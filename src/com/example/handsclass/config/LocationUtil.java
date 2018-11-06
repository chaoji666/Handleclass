package com.example.handsclass.config;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;


public class LocationUtil {
    // γ��
    public static double latitude = 0.0;
    // ����
    public static double longitude = 0.0;
    public static LocationManager locationManager;
    public static Location location;
    private static String provider;
    /**
     * ��ʼ��λ����Ϣ
     *
     * @param context
     */
    public static void initLocation(Context context) {
 
        LocationListener locationListener = new LocationListener() {
 
            @Override
            public void onProviderEnabled(String arg0) {
                // TODO Auto-generated method stub
 
            }
 
            @Override
            public void onProviderDisabled(String arg0) {
                // TODO Auto-generated method stub
 
            }
 
            @Override
            public void onLocationChanged(Location arg0) {
                // TODO Auto-generated method stub
                // ���µ�ǰ��γ��
            }

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}
        };
 
        //��ȡ��λ����
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //��ȡ��ǰ���õ�λ�ÿ�����
        List<String> list = locationManager.getProviders(true);
 
        if (list.contains(LocationManager.GPS_PROVIDER)) {
            //�Ƿ�ΪGPSλ�ÿ�����
            provider = LocationManager.GPS_PROVIDER;
        }
        else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            //�Ƿ�Ϊ����λ�ÿ�����
            provider = LocationManager.NETWORK_PROVIDER;
 
        } else {
            Toast.makeText(context, "���������GPS�Ƿ��",
                    Toast.LENGTH_LONG).show();
            return;
        }
 
      /*  if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return  ;
        }*/
 
 
        location = locationManager.getLastKnownLocation(provider);
 
 
        if (location != null) {
            //��ȡ��ǰλ�ã�����ֻ�õ��˾�γ��
            String stringPosition = "γ��Ϊ��" + location.getLatitude() + ",����Ϊ��"
                    + location.getLongitude();
            longitude=location.getLongitude();
            latitude=location.getLatitude();
            Toast.makeText(context, stringPosition, Toast.LENGTH_LONG).show();
 
        }
        //�󶨶�λ�¼�������λ���Ƿ�ı�
        //��һ������Ϊ���������͵ڶ�������Ϊ����λ�ñ仯��ʱ��������λ�����룩
        //����������Ϊλ�ñ仯�ļ������λ���ף����ĸ�����Ϊλ�ü�����
 
        locationManager.requestLocationUpdates(provider, 2000, 2, locationListener);
 
 
 
        }
 
 
    public static String getAddress(Location location,Context context) throws IOException {
        if(location==null){
            //LogUtils.INSTANCE.d_debugprint("����","δ�ҵ�location");
            return "";
        }
 
        Geocoder geocoder = new Geocoder(context);
        boolean flag = geocoder.isPresent();
        //LogUtils.INSTANCE.d_debugprint("λ����Ϣ","the flag is "+flag);
        StringBuilder stringBuilder = new StringBuilder();
        try {
 
            //���ݾ�γ�Ȼ�ȡ����λ����Ϣ
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
 
           // LogUtils.INSTANCE.d_debugprint("����",Double.toString(location.getLatitude()));
            //LogUtils.INSTANCE.d_debugprint("γ��",Double.toString(location.getLongitude()));
 
 
            //���ݵ�ַ��ȡ����λ����Ϣ
            //List<Address> addresses = geocoder.getFromLocationName( "�㶫ʡ�麣���������غ�·321��", 1);
 
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    stringBuilder.append(address.getAddressLine(i)).append("\n");
                }
                stringBuilder.append(address.getCountryName()).append("_");//����
                stringBuilder.append(address.getFeatureName()).append("_");//�ܱߵ�ַ
                stringBuilder.append(address.getLocality()).append("_");//��
                stringBuilder.append(address.getPostalCode()).append("_");
                stringBuilder.append(address.getCountryCode()).append("_");//���ұ���
                stringBuilder.append(address.getAdminArea()).append("_");//ʡ��
                stringBuilder.append(address.getSubAdminArea()).append("_");
                stringBuilder.append(address.getThoroughfare()).append("_");//��·
                stringBuilder.append(address.getSubLocality()).append("_");//������
                stringBuilder.append(address.getLatitude()).append("_");//����
                stringBuilder.append(address.getLongitude());//ά��
                /*System.out.println(stringBuilder.toString());*/
                //LogUtils.INSTANCE.d_debugprint("��ȡ���ĵ���λ��Ϊ��",stringBuilder.toString());
 
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Toast.makeText(context, "����", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}