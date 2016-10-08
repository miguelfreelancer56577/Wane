package app.wane.com.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import app.wane.com.model.PurchaseOrder;
import app.wane.com.wane.R;

/**
 * Created by Maricruz on 05/10/2016.
 */
public class AdapterPurchaseOrder extends ArrayAdapter {

    Activity context;
    List<PurchaseOrder> purchaseOrders;

    public AdapterPurchaseOrder(Activity context, List<PurchaseOrder> purchaseOrders) {
        super(context, R.layout.temple_pedidos, purchaseOrders);
        this.context = context;
        this.purchaseOrders = purchaseOrders;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View item = convertView;
        ViewHolderPurchaseOrder holder = new ViewHolderPurchaseOrder();

        if(item == null){

            LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(R.layout.temple_pedidos, null);

            holder.txtStatus = (TextView)item.findViewById(R.id.status);
            holder.txtDeliveryDate = (TextView)item.findViewById(R.id.deliverydate);
            holder.txtDeliveryAddress = (TextView)item.findViewById(R.id.deliveryaddress);

            item.setTag(holder);

        }else{
            holder = (ViewHolderPurchaseOrder)item.getTag();
        }
        holder.txtStatus.setText(purchaseOrders.get(position).getStatus());
        holder.txtDeliveryDate.setText(purchaseOrders.get(position).getDeliverydate());
        holder.txtDeliveryAddress.setText(purchaseOrders.get(position).getDeliveryaddress());

        return item;
    }

    protected class ViewHolderPurchaseOrder {
        TextView txtStatus;
        TextView txtDeliveryDate;
        TextView txtDeliveryAddress;
    }

}
