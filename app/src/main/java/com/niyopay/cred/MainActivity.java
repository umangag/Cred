package com.niyopay.cred;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by umangagarwal on 31,July,2019
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.backgroundText)
    TextView backgroundText;

    @BindView(R.id.etCardNumber)
    EditText etCardNumber;

    @BindView(R.id.txtCardNotFound)
    TextView txtCardNotFound;

    @BindView(R.id.ivCard)
    ImageView ivCard;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        etCardNumber.requestFocus();

        String placeHolderTxt = "XXXXXXXXXXXXXXXX";

        placeHolderTxt = placeHolderTxt.replaceAll("(.{4})", "$1 ");   //Add Space after 4 X's

        placeHolderTxt = placeHolderTxt.substring(0, placeHolderTxt.length() - 1);      //Remove last space from the placeholder String

        backgroundText.setText(placeHolderTxt);


        final String finalBackgroundText = placeHolderTxt;


        etCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String inputText = s.toString();

                String sortedTxt = inputText.replaceAll("\\D", "");

                sortedTxt = sortedTxt.replaceAll("(\\d{4})(?=\\d)(?=\\d)(?=\\d)", "$1 ");    //This will add space after 4 digits

                etCardNumber.removeTextChangedListener(this);

                int index = etCardNumber.getSelectionEnd();

                if (index == 5 || index == 10 || index == 15)
                    if (count > before)
                        index++;
                    else
                        index--;


                StringBuilder stringBuilder = new StringBuilder(finalBackgroundText);

                for (int i = 0; i < sortedTxt.length(); i++) {
                    stringBuilder.setCharAt(i, sortedTxt.charAt(i));
                }

                backgroundText.setText(stringBuilder.toString());

                etCardNumber.setText(sortedTxt);

                int cardImage = CommonUtils.setCardType(sortedTxt.replaceAll("\\s", ""));
                ivCard.setImageResource(cardImage);


                if (sortedTxt.length() == 19) {
                    if (CommonUtils.isCardValid(sortedTxt)) {
                        txtCardNotFound.setVisibility(View.VISIBLE);
                    } else {
                        txtCardNotFound.setVisibility(View.GONE);
                    }
                } else {
                    txtCardNotFound.setVisibility(View.GONE);
                }


                try {
                    etCardNumber.setSelection(index);
                } catch (Exception e) {
                    e.printStackTrace();
                    etCardNumber.setSelection(s.length() - 1);
                }
                etCardNumber.addTextChangedListener(this);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
