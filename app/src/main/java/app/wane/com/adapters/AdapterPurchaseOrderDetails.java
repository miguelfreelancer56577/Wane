package app.wane.com.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import app.wane.com.model.PurchaseOrderDetail;
import app.wane.com.wane.R;

/**
 * Created by Maricruz on 05/10/2016.
 */
public class AdapterPurchaseOrderDetails extends ArrayAdapter {

    Activity context;
    List<PurchaseOrderDetail> purchaseOrderDetails;

    public AdapterPurchaseOrderDetails(Activity context, List<PurchaseOrderDetail> purchaseOrderDetails) {
        super(context, R.layout.temple_pedidos_details, purchaseOrderDetails);
        this.context = context;
        this.purchaseOrderDetails = purchaseOrderDetails;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View item = convertView;
        ViewHolderPurchaseOrder holder = new ViewHolderPurchaseOrder();

        if(item == null){

            LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(R.layout.temple_pedidos_details, null);

            holder.txtSku = (TextView)item.findViewById(R.id.txtSku);
            holder.txtQuantity = (TextView)item.findViewById(R.id.txtQuantity);
            holder.txtShortDescription = (TextView)item.findViewById(R.id.txtShortDescription);

            item.setTag(holder);

        }else{
            holder = (ViewHolderPurchaseOrder)item.getTag();
        }
        holder.txtSku.setText(purchaseOrderDetails.get(position).getSku());
        holder.txtQuantity.setText(purchaseOrderDetails.get(position).getQuantity());
        holder.txtShortDescription.setText(purchaseOrderDetails.get(position).getShortdescription());

        return item;
    }

    protected class ViewHolderPurchaseOrder {
        TextView txtSku;
        TextView txtQuantity;
        TextView txtShortDescription;
    }

}
