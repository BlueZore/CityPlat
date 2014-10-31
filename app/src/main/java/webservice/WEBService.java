package webservice;

import java.util.HashMap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import android.os.StrictMode;
import android.util.Log;

public class WEBService {

	private SoapObject rpc;

	public WEBService(String[] Text, String[] Value,
			HashMap<String, String> ServiceParams) {
		rpc = new SoapObject(ServiceParams.get("NAMESPACE"),
				ServiceParams.get("METHOD_NAME"));
		for (int i = 0; i < Text.length; i++) {
			rpc.addProperty(Text[i], Value[i]);
		}
	}

	public SoapObject GetWebServiceSoapObject(
			HashMap<String, String> ServiceParams) {
		try {
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyIn = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);

			int timeout = 300000; // set timeout 5m

			MyAndroidHttpTransport transport = new MyAndroidHttpTransport(
					ServiceParams.get("URL"), timeout);

			transport.debug = true;

			transport.call(ServiceParams.get("SOAP_ACTION"), envelope);

			return (SoapObject) envelope.getResponse();

		} catch (Exception e) {

			e.printStackTrace();

			Log.e("~WSError", e.getMessage());

			return null;
		}
	}
}
