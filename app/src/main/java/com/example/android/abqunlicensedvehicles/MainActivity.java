package com.example.android.abqunlicensedvehicles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.layers.ArcGISMapImageLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.LayerList;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.MapView;

public class MainActivity extends AppCompatActivity {

    private static LayerList mOperationalLayers;
    private MapView mMapView;

    public static LayerList getOperationalLayerList() {
        return mOperationalLayers;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button selectLayers;

        mMapView = findViewById(R.id.mapView);

        selectLayers = (Button) findViewById(R.id.operationallayer);

        ArcGISMap mMap = new ArcGISMap(Basemap.Type.TOPOGRAPHIC, 35.13700577, -106.59444565, 12);


        ServiceFeatureTable serviceFeatureTable = new ServiceFeatureTable(getResources().getString(R.string.sample_service_url));
        ServiceFeatureTable serviceFeatureTable1 = new ServiceFeatureTable(getResources().getString(R.string.sample_service_url1));

        FeatureLayer featureLayer = new FeatureLayer(serviceFeatureTable);
        FeatureLayer featureLayer1 = new FeatureLayer(serviceFeatureTable1);

        mOperationalLayers = mMap.getOperationalLayers();

        mMap.getOperationalLayers().add(featureLayer);
        mMap.getOperationalLayers().add(featureLayer1);


        mMapView.setMap(mMap);

        selectLayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OperationalLayers.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.dispose();
    }
}
