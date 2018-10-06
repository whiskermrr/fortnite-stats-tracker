package com.example.mrr.fortnitetracker.view.weapons;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mrr.fortnitetracker.R;
import com.example.mrr.fortnitetracker.Utils.ProjectUtils;
import com.example.mrr.fortnitetracker.mappers.WeaponsMapper;
import com.example.mrr.fortnitetracker.models.weapons.Weapon;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailsFragment extends Fragment {

    public static final String KEY_WEAPON = "KEY_WEAPON";

    private Weapon weapon;

    @BindView(R.id.ivItemImage)
    ImageView ivItemImage;

    @BindView(R.id.tvItemName)
    TextView tvItemName;

    @BindView(R.id.tableItemDetails)
    TableLayout tableItemDetails;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        weapon = (Weapon) getArguments().getSerializable(KEY_WEAPON);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);
        ButterKnife.bind(this, view);
        loadImage();
        tvItemName.setText(weapon.getName());
        populateTableDetails();
        return view;
    }

    private void loadImage() {
        int colorResourceId = ProjectUtils.getColorResourceIdByRarity(weapon.getRarity());
        ivItemImage.setBackgroundColor(ContextCompat.getColor(getContext(), colorResourceId));

        int imageDrawableId = ProjectUtils.getDrawableIdByFileName(getContext(), weapon.getImageFileName());

        if(imageDrawableId > 0) {
            Picasso.with(getContext())
                    .load(imageDrawableId)
                    .fit()
                    .centerInside()
                    .into(ivItemImage);
        } else {
            Picasso.with(getContext())
                    .load(weapon.getImageUrl())
                    .fit()
                    .centerInside()
                    .into(ivItemImage);
        }
    }

    private void populateTableDetails() {
        List<Pair<String, String>> weaponAsDictionary = WeaponsMapper.
                transformToDictionary(
                        weapon,
                        getResources().getStringArray(R.array.weapon_details)
                );

        for(Pair<String, String> rowContent: weaponAsDictionary) {
            View view = new View(getActivity());
            view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDarkFactoryBlue));
            view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
            tableItemDetails.addView(view);
            tableItemDetails.addView(getItemDetailsRow(rowContent.first, rowContent.second));
        }
    }

    private TableRow getItemDetailsRow(String description, String details) {
        final TableRow row = (TableRow) LayoutInflater
                .from(getActivity())
                .inflate(R.layout.item_details_row, null);

        TextView tvDescription = row.findViewById(R.id.row_description);
        TextView tvDetails = row.findViewById(R.id.row_details);
        tvDescription.setText(description);
        tvDetails.setText(details);

        return row;
    }
}
