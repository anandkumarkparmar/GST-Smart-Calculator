package in.coloredfeather.gstsmartcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.calculations_first_input)
    TextView firstInputCaculations;

    @BindView(R.id.calculations_second_input)
    TextView secondInputCaculations;

    @BindView(R.id.calculations_total)
    TextView totalAmountCaculations;

    @BindView(R.id.calculations_gst_layout)
    LinearLayout gstLayoutCaculations;

    @BindView(R.id.calculations_gst_tag)
    TextView gstTagCaculations;

    @BindView(R.id.calculations_gst_amount)
    TextView gstAmountCaculations;

    @BindView(R.id.calculations_part_gst_amount)
    TextView partGSTAmountCalculations;

    @BindView(R.id.calculations_total_amount_tag)
    TextView totalAmountWithGSTTagCaculations;

    @BindView(R.id.calculations_total_amount_with_gst)
    TextView totalAmountWithGSTCaculations;

    @BindView(R.id.keyboard_clear)
    TextView clearKey;

    @BindView(R.id.change_gst_mode)
    TextView changeGSTMode;

    @BindView(R.id.keyboard_gst_5)
    TextView keyboardGST5;

    @BindView(R.id.keyboard_gst_12)
    TextView keyboardGST12;

    @BindView(R.id.keyboard_gst_18)
    TextView keyboardGST18;

    @BindView(R.id.keyboard_gst_28)
    TextView keyboardGST28;

    private Long firstNumber;
    private Long secondNumber;
    private Long totalAmount;
    private Double gstAmount;
    private Double totalAmountWithGST;

    private boolean firstNumberEditing = true;

    private static final int GST_MODE_POSITIVE = 1;
    private static final int GST_MODE_NEGATIVE = -1;
    private int gstMode = GST_MODE_POSITIVE;

    /**
     * 0-plus
     * 1-munis
     * 2-multiply
     * 3-divide
     */
    private int operation = 0;

    private Map<Integer, String> mapOperations = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        reset();

        mapOperations.put(0, "+");
        mapOperations.put(1, "-");
        mapOperations.put(2, "x");
        mapOperations.put(3, "/");
    }

    @OnClick(R.id.keyboard_0)
    protected void key0OnClick(){
        addNumberInCurrentInput(0);
    }

    @OnClick(R.id.keyboard_1)
    protected void key1OnClick(){
        addNumberInCurrentInput(1);
    }

    @OnClick(R.id.keyboard_2)
    protected void key2OnClick(){
        addNumberInCurrentInput(2);
    }

    @OnClick(R.id.keyboard_3)
    protected void key3OnClick(){
        addNumberInCurrentInput(3);
    }

    @OnClick(R.id.keyboard_4)
    protected void key4OnClick(){
        addNumberInCurrentInput(4);
    }

    @OnClick(R.id.keyboard_5)
    protected void key5OnClick(){
        addNumberInCurrentInput(5);
    }

    @OnClick(R.id.keyboard_6)
    protected void key6OnClick(){
        addNumberInCurrentInput(6);
    }

    @OnClick(R.id.keyboard_7)
    protected void key7OnClick(){
        addNumberInCurrentInput(7);
    }

    @OnClick(R.id.keyboard_8)
    protected void key8OnClick(){
        addNumberInCurrentInput(8);
    }

    @OnClick(R.id.keyboard_9)
    protected void key9OnClick(){
        addNumberInCurrentInput(9);
    }

    @OnClick(R.id.keyboard_plus)
    protected void keyPlusOnClick(){
        setOperation(0);
    }

    @OnClick(R.id.keyboard_minus)
    protected void keyMinusOnClick(){
        setOperation(1);
    }

    @OnClick(R.id.keyboard_multiply)
    protected void keyMultiplyOnClick(){
        setOperation(2);
    }

    @OnClick(R.id.keyboard_divide)
    protected void keyDivideOnClick(){
        setOperation(3);
    }

    @OnClick(R.id.keyboard_equals)
    protected void keyEqualsOnClick(){
        calculate();
    }

    /*@OnClick(R.id.keyboard_dot)
    protected void keyDotOnClick(){

    }*/

    @OnClick(R.id.keyboard_clear)
    protected void keyClearOnClick(){
        clear();
    }

    @OnClick(R.id.keyboard_gst_5)
    protected void keyGST5OnClick(){
        showGSTLayout(5);
    }

    @OnClick(R.id.keyboard_gst_12)
    protected void keyGST12OnClick(){
        showGSTLayout(12);
    }

    @OnClick(R.id.keyboard_gst_18)
    protected void keyGST18OnClick(){
        showGSTLayout(18);
    }

    @OnClick(R.id.keyboard_gst_28)
    protected void keyGST28OnClick(){
        showGSTLayout(28);
    }

    @OnClick(R.id.change_gst_mode)
    protected void changeGSTModeOnClick(){
        if(changeGSTMode.getText().equals("-")){
            gstMode = GST_MODE_NEGATIVE;

            keyboardGST5.setText("-5 %");
            keyboardGST12.setText("-12 %");
            keyboardGST18.setText("-18 %");
            keyboardGST28.setText("-28 %");

            changeGSTMode.setText("+");
        } else {
            gstMode = GST_MODE_POSITIVE;

            keyboardGST5.setText("5 %");
            keyboardGST12.setText("12 %");
            keyboardGST18.setText("18 %");
            keyboardGST28.setText("28 %");

            changeGSTMode.setText("-");
        }
    }

    private void calculate(){
        if(!firstNumberEditing) {
            if (operation == 0) {
                totalAmount = firstNumber + secondNumber;
            } else if (operation == 1) {
                totalAmount = firstNumber - secondNumber;
            } else if (operation == 2) {
                totalAmount = firstNumber * secondNumber;
            } else if (operation == 3) {
                totalAmount = firstNumber / secondNumber;
            }
        }

        totalAmountCaculations.setText("= " + formateAmount(totalAmount));
    }

    private void setOperation(int operation){
        firstNumberEditing = false;

        if(secondInputCaculations.getText().equals("")){
            secondInputCaculations.setText(mapOperations.get(operation) + " ");
            this.operation = operation;
        } else {
            secondInputCaculations.setText(secondInputCaculations.getText().toString().replace(mapOperations.get(this.operation), mapOperations.get(operation)));
            this.operation = operation;
            if(secondInputCaculations.getText().length() > 2){
                calculate();
            }
        }
    }

    private void addNumberInCurrentInput(int x){
        if(gstLayoutCaculations.getVisibility() == View.VISIBLE){
            reset();
        }

        if(firstNumberEditing){
            Long temp = (firstNumber * 10) + x;

            if(temp <= 999999999) {
                firstNumber = (firstNumber * 10) + x;
                firstInputCaculations.setText(formateAmount(firstNumber));

                totalAmount = firstNumber;
                calculate();
            }
        } else {
            Long temp = (secondNumber * 10) + x;

            if(temp <= 999999999) {
                secondNumber = (secondNumber * 10) + x;
                if (secondNumber != 0) {
                    secondInputCaculations.setText(mapOperations.get(operation) + " " + formateAmount(secondNumber));
                    clearKey.setText("C");
                    calculate();
                }
            }
        }
    }

    private void showGSTLayout(int gstPercentage){
        if(gstMode == GST_MODE_POSITIVE) {
            gstAmount = (totalAmount * (gstPercentage / 100.00));
            totalAmountWithGST = totalAmount + gstAmount;

            gstTagCaculations.setText("GST (" + gstPercentage + "%)");
            gstAmountCaculations.setText(formateAmount(gstAmount));
            partGSTAmountCalculations.setText("( CGST(" + (gstPercentage / 2.0) + "%) = " + formateAmount(gstAmount / 2.0) + " )\n( SGST(" + (gstPercentage / 2.0) + "%) = " + formateAmount(gstAmount / 2.0) + " )");
            totalAmountWithGSTTagCaculations.setText("Total Amount");
            totalAmountWithGSTCaculations.setText("= " + formateAmount(totalAmountWithGST));

            gstLayoutCaculations.setVisibility(View.VISIBLE);

            clearKey.setText("AC");
        } else {
            gstAmount = totalAmount - (totalAmount / ( 1 + (gstPercentage / 100.00)));
            totalAmountWithGST = totalAmount - gstAmount;

            gstTagCaculations.setText("GST (" + gstPercentage + "%)");
            gstAmountCaculations.setText("- " + formateAmount(gstAmount));
            partGSTAmountCalculations.setText("( CGST(" + (gstPercentage / 2.0) + "%) = - " + formateAmount(gstAmount / 2.0) + " )\n( SGST(" + (gstPercentage / 2.0) + "%) = - " + formateAmount(gstAmount / 2.0) + " )");
            totalAmountWithGSTTagCaculations.setText("Total Amount(without GST)");
            totalAmountWithGSTCaculations.setText("= " + formateAmount(totalAmountWithGST));

            gstLayoutCaculations.setVisibility(View.VISIBLE);

            clearKey.setText("AC");
        }
    }

    private void clear(){
        if(clearKey.getText().equals("AC")) {
            reset();
        } else {
            if(firstNumberEditing){
                reset();
            } else {
                secondNumber = 0L;
                totalAmount = firstNumber;
                totalAmountCaculations.setText("= " + formateAmount(totalAmount));
                secondInputCaculations.setText(mapOperations.get(operation) + " ");
                clearKey.setText("AC");
            }
        }
    }

    private void reset(){
        firstNumber = 0L;
        secondNumber = 0L;
        totalAmount = 0L;
        gstAmount = 0D;
        totalAmountWithGST = 0D;

        gstLayoutCaculations.setVisibility(View.GONE);
        firstInputCaculations.setText("" + firstNumber);
        secondInputCaculations.setText("");
        totalAmountCaculations.setText("= " + totalAmount);

        firstNumberEditing = true;
        clearKey.setText("C");
    }

    private String formateAmount(Long amount){
        DecimalFormat formatter = new DecimalFormat("##,##,###");
        return formatter.format(amount);
    }

    private String formateAmount(Double amount){
        DecimalFormat formatter = new DecimalFormat("##,##,###.##");
        return formatter.format(amount);
    }
}
