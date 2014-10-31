package base;

public class MapClass {
	String Map = "";
	String Key = "AqpjB-zTBd9X8gpP9--fVetVZW5zDyS5-R0xEBqYTGa16SRfOjv4y5x7cgdVyibd";

	/**
	 * BingMap地图初始化
	 * 
	 * @param Lng
	 *            组织机构经纬度
	 * @param Lat
	 *            组织机构经纬度
	 */
	public MapClass(String Lng, String Lat) {
		Map = "<!DOCTYPE html><html><head><meta charset='utf-8'/><link rel='stylesheet'type='text/css'href='css/index.css'/><script type='text/javascript'src='http://ecn.dev.virtualearth.net/mapcontrol/mapcontrol.ashx?v=7.0'></script><script>var map=null;var click;var lng=[Lng];var lat=[Lat];function GetMap(position){map=new Microsoft.Maps.Map(document.getElementById('divMap'),{credentials:'[Key]',mapTypeId:Microsoft.Maps.MapTypeId.aerial,enableClickableLogo:false,enableSearchLogo:false,center:new Microsoft.Maps.Location(lat,lng),zoom:15});document.getElementById('lng').value = '[Location' + lat + ',' + lng + ']';[AutoLocal]}function AutoLocal(){if(navigator.geolocation){navigator.geolocation.getCurrentPosition(showPosition)}}function showPosition(position){lng=position.coords.longitude;lat=position.coords.latitude;document.getElementById('lng').value=lat+','+lng;clearAllAndAddPoint()}function clearAllAndAddPoint(){map.entities.clear();var pushpinOptions={draggable:true};var pushpin=new Microsoft.Maps.Pushpin(new Microsoft.Maps.Location(lat,lng),pushpinOptions);map.setView({center:new Microsoft.Maps.Location(lat,lng),zoom:15});var pushpindrag=Microsoft.Maps.Events.addHandler(pushpin,'drag',onDragDetails);map.entities.push(pushpin)}onDragDetails=function(e){document.getElementById('lng').value=e.entity.getLocation();};function setPointForAndroid(_lng,_lat){var pushpin=new Microsoft.Maps.Pushpin(new Microsoft.Maps.Location(_lat,_lng),null);map.setView({center:new Microsoft.Maps.Location(_lat,_lng),zoom:15});map.entities.push(pushpin)}function getPointForAndroid(){var point=document.getElementById('lng').value;window.demo.clickOnAndroid_getPoint(point.length==0?'尚未获取到':point)}</script><title>Bing Maps</title></head><body onload='GetMap();'><input id='lng'style='display:none;'/><div id='divMap'style='width:100%; height:92%; position:absolute;'></div></body></html>";

		Map = Map.replace("[Key]", Key);
		Map = Map.replace("[Lng]", Lng);
		Map = Map.replace("[Lat]", Lat);
	}

	/**
	 * 定位
	 */
	public String AutoLocal() {
		Map = Map.replace("[AutoLocal]", "clearAllAndAddPoint();AutoLocal();");
		return Map;
	}

	/**
	 * 完成
	 */
	public String Finish() {
		Map = Map.replace("[AutoLocal]", "");
		return Map;
	}
}
