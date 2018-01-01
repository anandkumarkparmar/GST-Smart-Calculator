package in.coloredfeather.gstsmartcalculator;

import android.os.Bundle;

public class GSTCalcActivity extends CalcActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setGstMode(GST_MODE_POSITIVE);
    }
}
