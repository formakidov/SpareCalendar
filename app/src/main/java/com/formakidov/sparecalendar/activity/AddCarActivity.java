package com.formakidov.sparecalendar.activity;

import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.formakidov.sparecalendar.Constants;
import com.formakidov.sparecalendar.R;
import com.formakidov.sparecalendar.db.repository.CarsRepository;
import com.formakidov.sparecalendar.model.Car;
import com.formakidov.sparecalendar.tools.Formatter;
import com.formakidov.sparecalendar.tools.Tools;

import java.sql.SQLException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCarActivity extends BaseActivity {

    @Bind(R.id.name)
    EditText etName;
    @Bind(R.id.comment)
    TextView tvComment;
    @Bind(R.id.mileage)
    EditText etMileage;
    @Bind(R.id.gearbox_type_spinner)
    Spinner spinnerGearboxType;

    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        ButterKnife.bind(this);

        setupViews();
    }

    @Override
    protected void setupViews() {
        long carId = getIntent().getLongExtra(Constants.EXTRA_CAR_ID, 0);
        int titleId;
        if (carId == 0) {
            titleId = R.string.toolbar_title_car_add;
            car = new Car(0);
        } else {
            CarsRepository repo = new CarsRepository(this);
            try {
                car = repo.findById(carId);
            } catch (SQLException e) {
                // TODO: 07.03.2016
                e.printStackTrace();
            }
            titleId = R.string.toolbar_title_car_edit;
        }

        initToolbar(titleId);

        this.etName.setText(car.getName());
        this.etMileage.setText(String.valueOf(car.getMileage()));

        String comment = Formatter.formatParameterValue(this, car.getComment(), R.string.no_comment);
        this.tvComment.setText(comment);

        spinnerGearboxType.setSelection(car.getGearboxType());
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.toolbar_action_ok).setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_action_ok:
                saveCar();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.comment)
    void onCommentClick() {
        new MaterialDialog.Builder(this)
                .title(R.string.comment)
                .inputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE)
                .input(getString(R.string.comment_hint), car.getComment(), new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        car.setComment(input.toString().trim());
                        tvComment.setText(input.toString().trim().isEmpty() ? getString(R.string.no_comment) : input);
                    }
                }).show();
    }

    public void saveCar() {
        String name = etName.getText().toString().trim();
        if (name.isEmpty()) {
            // TODO: 06.03.2016
            return;
        }
        long mileage = Long.parseLong(etMileage.getText().toString());
        if (mileage == 0) {
            // TODO: 06.03.2016
            return;
        }

        car.setName(name);
        car.setMileage(mileage);
        car.setGearboxType(0);// TODO: 07.03.2016
        car.setLatestUpdate(System.currentTimeMillis());
        car.setComment(tvComment.getText().toString());
        CarsRepository repo = new CarsRepository(this);
        try {
            repo.save(car);
        } catch (SQLException e) {
            // TODO: 06.03.2016
            e.printStackTrace();
            return;
        }
        Tools.hideKeyboard(this);
        finish();
    }

}
