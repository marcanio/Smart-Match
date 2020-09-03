package main.dateofbirth;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class dateofbirth extends AppCompatActivity
{
	String password;
	User user;
	SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");
	private DatePicker ageSelectionPicker;
	private Button ageContinueButton;
	private int ageLimit = 13;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_age);

		Intent intent = getIntent();
		user = (User) intent.getSerializableExtra("classUser");
		password = intent.getStringExtra("password");

		ageSelectionPicker = findViewById(R.id.ageSelectionPicker);


		ageContinueButton = findViewById(R.id.ageContinueButton);

		ageContinueButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				openHobbiesEntryPage();
			}
		});


	}

	public void openHobbiesEntryPage()
	{
		int age = getAge(ageSelectionPicker.getYear(), ageSelectionPicker.getMonth(), ageSelectionPicker.getDayOfMonth());
		if (age > ageLimit)
		{
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, ageSelectionPicker.getYear());
			cal.set(Calendar.MONTH, ageSelectionPicker.getMonth());
			cal.set(Calendar.DAY_OF_MONTH, ageSelectionPicker.getDayOfMonth());
			Date dateOfBirth = cal.getTime();
			String strDateOfBirth = dateFormatter.format(dateOfBirth);
			user.setDateOfBirth(strDateOfBirth);

			Intent intent = new Intent(this, RegisterHobby.class);
			intent.putExtra("password", password);
			intent.putExtra("classUser", user);
			startActivity(intent);
		}
		else
			{
			Toast.makeText(getApplicationContext(), "Age of the user should be greater than " + ageLimit + " !!!", Toast.LENGTH_SHORT).show();
		}

	}

	private int getAge(int year, int month, int day)
	{
		Calendar dateOfBirth = Calendar.getInstance();
		Calendar today = Calendar.getInstance();

		dateOfBirth.set(year, month, day);

		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		if (today.get(Calendar.DAY_OF_YEAR) < dateOfBirth.get(Calendar.DAY_OF_YEAR))
		{
			age--;
		}

		return age;
	}
}