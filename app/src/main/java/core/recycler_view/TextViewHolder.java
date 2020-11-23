package core.recycler_view;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import core.shared.Traceable;
import core.activities.R;
import core.fragments.ImageDialog;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Accessors(fluent = true, prefix = "_")
public class TextViewHolder extends RecyclerView.ViewHolder implements Traceable {
    TextView _view;

    public TextViewHolder(@NonNull View itemView, @NonNull final FragmentActivity activity) {
        super(itemView);
        _view = itemView.findViewById(R.id.textView);
        _view.setOnClickListener(self -> {
            trace("Item has been clicked...");
            new ImageDialog(_view.getText().toString()).show(activity.getSupportFragmentManager(), null);
        });
    }
}

