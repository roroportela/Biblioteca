package biblioteca.studio.com.biblioteca;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap = null;
    String[] localizacao = null;

    public MapsActivity() {
    }

    @SuppressLint("ValidFragment")
    public MapsActivity(String[] localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng local = new LatLng(Double.parseDouble(localizacao[0]), Double.parseDouble(localizacao[1]));
        mMap.addMarker(new MarkerOptions().position(local).title(localizacao[2]));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 16.0f));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent intent = new Intent(getActivity(), FragmentMaps.class);
                intent.putExtra("lat", localizacao[0]);
                intent.putExtra("lng", localizacao[1]);
                startActivity(intent);
            }
        });
    }
}