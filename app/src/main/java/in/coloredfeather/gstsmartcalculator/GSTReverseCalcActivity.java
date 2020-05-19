package in.coloredfeather.gstsmartcalculator;

import android.os.Bundle;

public class GSTReverseCalcActivity extends CalcActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setGstMode(GST_MODE_NEGATIVE);
    }
}
