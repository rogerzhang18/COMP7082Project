package ca.bcit.comp7082.Assignment1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class Gallery extends AppCompatActivity {
    private ImageAdapter imageAdapter;
    private final ArrayList<String> f = new ArrayList<>();// list of file paths
    private File[] listFile;
    private TextView dateView;
    private int year, month, day;
    private EditText location;
    private GridView imagegrid;
    private String [] locationName;
    private final File sdCard = Environment.getExternalStorageDirectory();
    public static int resID;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        getFromSdcard();
        imagegrid = findViewById(R.id.PhoneImageGrid);
        imageAdapter = new ImageAdapter();
        imagegrid.setAdapter(imageAdapter);
        Calendar calendar;

        imagegrid.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id)
            {
                Toast.makeText(getBaseContext(),
                        "pic" + (position + 1) + " selected",
                        Toast.LENGTH_SHORT).show();

                //uploadMultipart(f.get(position));

                Intent intent = new Intent(Gallery.this,FullImage.class);
                intent.putExtra("path", f.get(position));
                intent.putExtra("imgPos",position);
                Gallery.this.startActivity(intent);
            }
        });

        dateView = findViewById(R.id.plain_text_input1);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        Button button = findViewById(R.id.datepicker);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setDate();
            }
        });

        Button buttonSearch = findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                location = findViewById(R.id.locationText);
                f.clear();
                File file = new File(sdCard.getAbsolutePath() + "/PhotoApplication");

                String date = (String) dateView.getText();
                date = date.replace("/", "");

                if (file.isDirectory()) {
                    listFile = file.listFiles();
                    for (File aListFile : listFile) {
                        locationName = aListFile.getAbsolutePath().split("_");
                        if ("all".equals(location.getText().toString())) {
                            f.add(aListFile.getAbsolutePath());
                            continue;
                        }
                        if ((locationName[locationName.length - 1].equals(location.getText().toString()) || (locationName[locationName.length - 2].equals(location.getText().toString())) || locationName[1].equals(location.getText().toString())) && date.equals(locationName[2])) {
                            f.add(aListFile.getAbsolutePath());
                        }
                    }
                }
                imageAdapter = new ImageAdapter();
                imagegrid.setAdapter(imageAdapter);
            }
        });

    }
    public void uploadMultipart(String filepath) {
        //getting name for the image
        String name = "test";
        String uploadurl = "http://142.232.139.102/AndroidUploadImage/upload.php";
        //getting the actual path of the image
        Log.e("Upload", filepath);
        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();
            Log.e("Upload", filepath);

            //Creating a multi part request
            new MultipartUploadRequest(this, uploadId, uploadurl)
                    .addFileToUpload(filepath, "image") //Adding file
                    .addParameter("name", name) //Adding text parameter to the request
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    //method to get the file path from uri
    private void getFromSdcard() {
        File file = new File(sdCard.getAbsolutePath() + "/PhotoApplication");

        if (file.isDirectory()) {
            listFile = file.listFiles();
            for (File aListFile : listFile) {
                f.add(aListFile.getAbsolutePath());
            }
        }
    }

    private class ImageAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;
        String[] separated;

        private ImageAdapter() {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return f.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.galleryitem, parent, false);
                holder.imageview = convertView.findViewById(R.id.thumbImage);
                holder.textView = convertView.findViewById(R.id.imageText);
                //holder.textView2 = (TextView) convertView.findViewById(R.id.imageText2);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            separated = f.get(position).split("_");

            holder.textView.setText(separated[1]);
            //holder.textView2.setText(separated[4]);

            Bitmap myBitmap = BitmapFactory.decodeFile(f.get(position));
            holder.imageview.setImageBitmap(myBitmap);
            return convertView;
        }
    }

    class ViewHolder {
        ImageView imageview;
        TextView textView;
    }

    @SuppressWarnings("deprecation")
    private void setDate() {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Please select a date",
                Toast.LENGTH_SHORT)
                .show();
    }

    @SuppressWarnings("deprecation")
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private final DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setTextSize(25);

        String dd = "";
        if (day < 10)
        {
            dd = dd.concat("0").concat(Integer.toString(day));
        } else {
            dd = dd.concat(Integer.toString(day));
        }
        if (month < 10) {
            dd = dd.concat("/0").concat(Integer.toString(month)).concat("/").concat(Integer.toString(year));
        } else {
            dd = dd.concat("/").concat(Integer.toString(month)).concat("/").concat(Integer.toString(year));
        }
        dateView.setText(dd);
    }
}
