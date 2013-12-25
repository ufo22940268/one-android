package me.biubiubiu.one.ui;

import me.biubiubiu.one.R;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPlanNode;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKShareUrlResult;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
/**
 * 此demo用来展示如何用自己的数据构造一条路线在地图上绘制出来
 *
 */
public class RouteActivity  extends BaseActivity{

    private MKSearch mMKSearch;
    //地图相关
    MapView mMapView = null;    // 地图View
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customroute);
        CharSequence titleLable="路线规划功能——自设路线示例";
        setTitle("路线");
        //初始化地图
        mMapView = (MapView)findViewById(R.id.bmapView);
        mMapView.getController().enableClick(true);
        mMapView.getController().setZoom(13);

        // /** 演示自定义路线使用方法
        //  *  在北京地图上画一个北斗七星
        //  *  想知道某个点的百度经纬度坐标请点击：http://api.map.baidu.com/lbsapi/getpoint/index.html
        //  */
        // GeoPoint p1 = new GeoPoint((int)(39.9411 * 1E6),(int)(116.3714 * 1E6));
        // GeoPoint p2 = new GeoPoint((int)(39.9498 * 1E6),(int)(116.3785 * 1E6));
        // GeoPoint p3 = new GeoPoint((int)(39.9436 * 1E6),(int)(116.4029 * 1E6));
        // GeoPoint p4 = new GeoPoint((int)(39.9329 * 1E6),(int)(116.4035 * 1E6));
        // GeoPoint p5 = new GeoPoint((int)(39.9218 * 1E6),(int)(116.4115 * 1E6));
        // GeoPoint p6 = new GeoPoint((int)(39.9144 * 1E6),(int)(116.4230 * 1E6));
        // GeoPoint p7 = new GeoPoint((int)(39.9126 * 1E6),(int)(116.4387 * 1E6));
        // //起点坐标
        // GeoPoint start = p1;
        // //终点坐标
        // GeoPoint stop  = p7;
        MKPlanNode start = new MKPlanNode();
        start.pt = new GeoPoint((int) (39.915 * 1E6), (int) (116.404 * 1E6));
        MKPlanNode end = new MKPlanNode();
        end.pt = new GeoPoint(40057031, 116307852);// 设置驾车路线搜索策略，时间优先、费用最少或距离最短
        mMKSearch = new MKSearch();
        mMKSearch.setDrivingPolicy(MKSearch.ECAR_TIME_FIRST);
        mMKSearch.drivingSearch(null, start, null, end);
        mMKSearch.init(getMyApp().mBMapManager, mMKSearchListener);
    }

    private MKSearchListener mMKSearchListener =  new MKSearchListener() {

            public void onGetDrivingRouteResult(MKDrivingRouteResult result,
                                                int error) {
                if (result == null) {
                    return;
                }
                RouteOverlay routeOverlay = new RouteOverlay(RouteActivity.this, mMapView);  // 此处仅展示一个方案作为示例
                routeOverlay.setData(result.getPlan(0).getRoute(0));
                mMapView.getOverlays().add(routeOverlay);
                mMapView.refresh();
            }

            public void onGetTransitRouteResult(MKTransitRouteResult res,
                                                int error) {

            }

            public void onGetWalkingRouteResult(MKWalkingRouteResult res,
                                                int error) {

            }
            public void onGetAddrResult(MKAddrInfo res, int error) {
            }
            public void onGetPoiResult(MKPoiResult res, int arg1, int arg2) {
            }
            public void onGetBusDetailResult(MKBusLineResult result, int iError) {
            }

            @Override
            public void onGetSuggestionResult(MKSuggestionResult res, int arg1) {
            }

            @Override
            public void onGetPoiDetailSearchResult(int type, int iError) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onGetShareUrlResult(MKShareUrlResult result, int type,
                                            int error) {
                // TODO Auto-generated method stub

            }
        };
    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }
    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        mMapView.destroy();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mMapView.onRestoreInstanceState(savedInstanceState);
    }
}
