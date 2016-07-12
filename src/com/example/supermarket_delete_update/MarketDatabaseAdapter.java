package com.example.supermarket_delete_update;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class MarketDatabaseAdapter {
	MarketHelper helper;
	public MarketDatabaseAdapter(Context context){
		helper=new MarketHelper(context);
	}
	public long InsertData(String Type, String Quantity ,String Price){
		SQLiteDatabase db=helper.getWritableDatabase();
		ContentValues contentValuse=new ContentValues();
		contentValuse.put(MarketHelper.TYPE, Type);
		contentValuse.put(MarketHelper.QUANTITY, Quantity);
		contentValuse.put(MarketHelper.PRICE, Price);
		long id=db.insert(MarketHelper.TABLE_NAME, null, contentValuse);
		return id;
	}
	public String getAllData(){
		SQLiteDatabase db=helper.getWritableDatabase();
		String [] columns={MarketHelper.UID , MarketHelper.TYPE,MarketHelper.QUANTITY,MarketHelper.PRICE};
		Cursor cursor=db.query( MarketHelper.TABLE_NAME, columns, null, null, null,null,null);
		StringBuffer buffer=new StringBuffer();
		while (cursor.moveToNext()){
			int cid=cursor.getInt(0);
			String Type=cursor.getString(1);
			String Quantity=cursor.getString(2);
			String Price=cursor.getString(3);
			buffer.append(cid+" "+ Type+" "+Quantity+" "+Price+"\n");
		}
		
		return buffer.toString();
	}

	 public int DeleteData(String OldType){
		    
	    	SQLiteDatabase db=helper.getWritableDatabase();
			String whereArgs[]={OldType};
			int count=db.delete(MarketHelper.TABLE_NAME,  MarketHelper.TYPE+" =?", whereArgs);
			return count;
	    
	    }
	 public int UpdateData(String OldName,String NewName){
	    	SQLiteDatabase db=helper.getWritableDatabase();
			ContentValues contentValuse=new ContentValues();
			contentValuse.put(MarketHelper.TYPE, NewName);
			String whereArgs[]={OldName};
			int count=db.update(MarketHelper.TABLE_NAME, contentValuse, MarketHelper.TYPE+" =?", whereArgs);
			return count;
	    }
	
	static class MarketHelper extends SQLiteOpenHelper{
		private static final String DATABASE_NAME="Market_DataBase";
		private static final String TABLE_NAME="MARKETTABLE";
		private static final int DATABASE_VERSION=6;
		private static final String UID="_id";
		private static final String TYPE="Type";
		private static final String QUANTITY="Quantity";
		private static final String PRICE="Price";
		private Context context;
		public MarketHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			this.context=context;
			Toast.makeText(context, "Costructor", Toast.LENGTH_LONG).show();
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try {
				db.execSQL("CREATE TABLE "+TABLE_NAME+"("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+TYPE+" VARCHAR(255) ,"+QUANTITY+" VARCHAR(255) ,"+PRICE+" VARCHAR(255));");
				Toast.makeText(context, "OnCreat Method", Toast.LENGTH_LONG).show();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
			}
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			try {
				db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
				onCreate(db);
				Toast.makeText(context, "OnUpgrade Method", Toast.LENGTH_LONG).show();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
			}

		}
	}

}
