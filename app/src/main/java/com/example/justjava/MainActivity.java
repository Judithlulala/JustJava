package com.example.justjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This app displays an order form to order coffee.   boolean hasWhippedCream=false;
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    int price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the order button is clicked.
     */

   /* private boolean hasCream(){
        CheckBox hasCream=(CheckBox) findViewById(R.id.toppings);
        hasCream.is
    }

    */
    public void submitOrder(View view) {

        CheckBox hasCream = (CheckBox) findViewById(R.id.toppings);
        boolean haswhippedCream = hasCream.isChecked();
        CheckBox hasChoco = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = hasChoco.isChecked();
        EditText nameEdit = (EditText) findViewById(R.id.name_edit);
        String customer = nameEdit.getText().toString();

        price = calculatePrice(haswhippedCream, hasChocolate);
        String message = createOrderSummary(price, haswhippedCream, hasChocolate, customer);

        Intent intent= new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for"+customer);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);

        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given price on the screen.
     */


    /**
     * This method is called when the price add button is clicked.
     */

    public void increment(View view) {
        if (quantity == 100) {
        Toast.makeText(this,R.string.toast_increment,Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;

        display(quantity);

    }


    /**
     * This method is called when the price decrease button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {

            Toast.makeText(this, R.string.toast_decrement,Toast.LENGTH_SHORT).show();
            return;


        }
        quantity = quantity - 1;

        display(quantity);

    }


//    private void orderSummay(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.orderSummary);
//        orderSummaryTextView.setText(message);
//    }

    private int calculatePrice(boolean haswhippedCream, boolean hasChocolate) {
        int basePrice = 5;
        if (haswhippedCream) {
            basePrice = basePrice + 1;}
            if (hasChocolate) {
            basePrice = basePrice + 2;
        } else {
            basePrice = basePrice;
        }
        int totalPrice = basePrice * quantity;
        return totalPrice;
    }

    private String createOrderSummary(int priceOdOrder, boolean hasWhippedCream, boolean hasChocolate, String name) {
        String orderSummary = getString(R.string.order_summary_name)
        + "\nQuantity: " + quantity +
                "\nHas Whipped Cream: " + hasWhippedCream +
                "\nHas Chocolate: " + hasChocolate +
                "\nTotal: $" + priceOdOrder +
                getString(R.string.thank_you);
        return orderSummary;

    }
}