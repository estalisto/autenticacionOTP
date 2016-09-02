package com.google.android.apps.authenticator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.android.apps.authenticator.entities.AccesoEntities;
import com.google.android.apps.authenticator2.R;

import java.util.List;

/**
 * Created by Stalyn on 24/07/2016.
 */
public class ViewOTPAdapter extends BaseAdapter {

    private Context context;
    private List<AccesoEntities> lstAcceso;
    private AccesoEntities accesoItem;
    private LayoutInflater inflater;

    public ViewOTPAdapter(Context context, List<AccesoEntities> listadoAcceso){
        this.context = context;
        this.lstAcceso = listadoAcceso;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lstAcceso.size();
    }

    @Override
    public Object getItem(int position) {
        return lstAcceso.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.details_otp_layout, parent, false);
        accesoItem = (AccesoEntities) getItem(position);
        ((TextView) convertView.findViewById(R.id.id_otp)).setText(accesoItem.getOtp());
        ((TextView) convertView.findViewById(R.id.fecha)).setText(accesoItem.getFecha_envio());
        return convertView;
    }
}
