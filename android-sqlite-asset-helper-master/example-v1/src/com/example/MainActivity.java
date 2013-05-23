package com.example;

import java.io.ByteArrayInputStream;

import android.app.ListActivity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

	private Cursor employees;
	private MyDatabase db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		db = new MyDatabase(this);
		employees = db.getEmployees(); // you would not typically call this on the main thread


//		 byte[] blob = employees.getBlob(employees.getColumnIndex(db.getEmployees()));
//		    ByteArrayInputStream inputStream = new ByteArrayInputStream(blob);
//		    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//		    
		ListAdapter adapter = new SimpleCursorAdapter(this, 
				android.R.layout.simple_list_item_1, 
				employees, 
				new String[] {"CelebrityImage"}, 
				new int[] {android.R.id.text1});

		getListView().setAdapter(adapter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		employees.close();
		db.close();
	}

}