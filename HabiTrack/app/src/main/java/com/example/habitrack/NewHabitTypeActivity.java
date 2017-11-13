package com.example.habitrack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class NewHabitTypeActivity extends AppCompatActivity {

    /* declaring variables */
    ArrayList<Integer> plan = new ArrayList<Integer>();
    Switch sundaySwitch;
    Switch mondaySwitch;
    Switch tuesdaySwitch;
    Switch wednesdaySwitch;
    Switch thursdaySwitch;
    Switch fridaySwitch;
    Switch saturdaySwitch;

    /**
     * Handles the creation of Habit Types
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit_type);
        final HabitTypeController htc = new HabitTypeController(this);


        /* Handles when user wishes to Create using the User input fields */
        Button createButton = (Button) findViewById(R.id.button6);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Initializing */
                EditText titleEntry = (EditText) findViewById(R.id.editText3);
                EditText reasonEntry = (EditText) findViewById(R.id.editText4);
                EditText dateEntry = (EditText) findViewById(R.id.editText5);

                String title = titleEntry.getText().toString();
                String reason = reasonEntry.getText().toString();

                /*
                Expected format for date string : "MM/dd/yyy"
                Guide from https://alvinalexander.com/java/simpledateformat-convert-string-to-date-formatted-parse
                 to convert String to date
                 */
                String dateString = dateEntry.getText().toString();
                String expectedPattern = "MM/dd/yyyy";
                SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern, Locale.CANADA);
                Calendar date = Calendar.getInstance();
                try {
                    /* Using the previously described expected pattern, making sure it was inputted correctly */
                    Date dateDate = formatter.parse(dateString);
                    date.setTime(dateDate);

                    /* exception, will make date of creation current date if not entered correctly or specified */
                } catch (ParseException e) {
                    e.printStackTrace();
                    date = Calendar.getInstance();
                }

                /* initialize switch toggles */
                sundaySwitch = (Switch)findViewById(R.id.sunday);
                mondaySwitch = (Switch) findViewById(R.id.monday);
                tuesdaySwitch = (Switch) findViewById(R.id.tuesday);
                wednesdaySwitch = (Switch) findViewById(R.id.wednesday);
                thursdaySwitch = (Switch) findViewById(R.id.thursday);
                fridaySwitch = (Switch) findViewById(R.id.friday);
                saturdaySwitch = (Switch) findViewById(R.id.saturday);

                /* Handle for whichever day the user wishes to do the Habit.
                * Adds it to the plan */
                if(sundaySwitch.isChecked()) {
                    plan.add(Calendar.SUNDAY);
                }
                if(mondaySwitch.isChecked()) {
                    plan.add(Calendar.MONDAY);
                }
                if(tuesdaySwitch.isChecked()) {
                    plan.add(Calendar.TUESDAY);
                }
                if(wednesdaySwitch.isChecked()) {
                    plan.add(Calendar.WEDNESDAY);
                }
                if(thursdaySwitch.isChecked()) {
                    plan.add(Calendar.THURSDAY);
                }
                if(fridaySwitch.isChecked()) {
                    plan.add(Calendar.FRIDAY);
                }
                if(saturdaySwitch.isChecked()) {
                    plan.add(Calendar.SATURDAY);
                }

                /* Adds the new Habit Type to the Habit Type Controller */
                if ((!(title.equals("")) && !(reason.equals("")) && plan != null)) {
                    htc.createNewHabitType(title, reason, date, plan);


                }

                /* Handles all errors that may occur creating new Habit Type.
                 * Notifies the User  */
                else {
                    Toast.makeText(getApplicationContext(), "Invalid Creation", Toast.LENGTH_SHORT).show();


                }

                finish();
            }
        });

    }

    /* Experimental way of moving the switches and adding to a seperate function  */
    public void CheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.isChecked()) {
            if ((buttonView.getId()) == sundaySwitch.getId()) {
                plan.add(Calendar.SUNDAY);
            }
            if ((buttonView.getId()) == mondaySwitch.getId()) {
                plan.add(Calendar.MONDAY);
            }
            if ((buttonView.getId()) == tuesdaySwitch.getId()) {
                plan.add(Calendar.TUESDAY);
            }
            if ((buttonView.getId()) == wednesdaySwitch.getId()) {
                plan.add(Calendar.WEDNESDAY);
            }
            if ((buttonView.getId()) == thursdaySwitch.getId()) {
                plan.add(Calendar.THURSDAY);
            }
            if ((buttonView.getId()) == fridaySwitch.getId()) {
                plan.add(Calendar.FRIDAY);
            }
            if ((buttonView.getId()) == saturdaySwitch.getId()) {
                plan.add(Calendar.SATURDAY);
            }
        }

    }
    /*

    public void CheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(sundaySwitch.isChecked()) {
            plan.add(Calendar.SUNDAY);
        }
        if(mondaySwitch.isChecked()) {
            plan.add(Calendar.MONDAY);
        }
        if(tuesdaySwitch.isChecked()) {
            plan.add(Calendar.TUESDAY);
        }
        if(wednesdaySwitch.isChecked()) {
            plan.add(Calendar.WEDNESDAY);
        }
        if(thursdaySwitch.isChecked()) {
            plan.add(Calendar.THURSDAY);
        }
        if(fridaySwitch.isChecked()) {
            plan.add(Calendar.FRIDAY);
        }
        if(saturdaySwitch.isChecked()) {
            plan.add(Calendar.SATURDAY);
        }
    }
    */


}
