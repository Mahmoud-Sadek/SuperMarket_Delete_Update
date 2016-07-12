package com.example.supermarket_delete_update;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	MarketDatabaseAdapter MarketHelper;
    EditText ItemType;
    EditText ItemQuantity;
    EditText ItemPrice;
    Button AddItem;
    Button ViewData;
    Button DeleteItem;
    Button Update;
    EditText UpdateType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         MarketHelper=new MarketDatabaseAdapter(this);
        
        
        ItemType=(EditText)findViewById(R.id.item_txt);
        ItemQuantity=(EditText)findViewById(R.id.Quantityt_txt);
        ItemPrice=(EditText)findViewById(R.id.Price_txt);
        
        AddItem=(Button)findViewById(R.id.add_btn);
        AddItem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				String Type=ItemType.getText().toString();
				String Quantity=ItemQuantity.getText().toString();
				String Price=ItemPrice.getText().toString();
				   
				long id=MarketHelper.InsertData(Type,Quantity,Price);
				if(id<0){
			Toast.makeText(getBaseContext(), " UnSuccessful ", Toast.LENGTH_LONG).show();
				}else {
					Toast.makeText(getBaseContext(), " Successful Insert ", Toast.LENGTH_LONG).show();
				}
			}
		});
        
        ViewData=(Button)findViewById(R.id.view_btn);
        ViewData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				String Data=MarketHelper.getAllData();
				Toast.makeText(getBaseContext(), Data, Toast.LENGTH_LONG).show();
			}
		});
        DeleteItem=(Button)findViewById(R.id.Delet_btn);
        DeleteItem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
             String oldType=ItemType.getText().toString();
				int count =MarketHelper.DeleteData(oldType);
				Toast.makeText(getBaseContext(), "Deleted \n count"+count  , Toast.LENGTH_LONG).show();			
			}
		});
        
        UpdateType=(EditText)findViewById(R.id.Update_txt);
        Update=(Button)findViewById(R.id.Update_btn);
        Update.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String oldType=ItemType.getText().toString();
				String NewType=UpdateType.getText().toString();
				
				int count=MarketHelper.UpdateData( oldType, NewType);
				Toast.makeText(getBaseContext(), "Updated \n count"+count  , Toast.LENGTH_LONG).show();
				}
		});
    }

    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
