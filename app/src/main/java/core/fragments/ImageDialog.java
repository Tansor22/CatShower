package core.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import core.shared.Traceable;
import core.activities.R;
import core.async.ImageLoader;
import core.async.TaskRunner;
import core.cataas.CATAASAccessor;
import core.cataas.CatType;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ImageDialog extends DialogFragment implements Traceable {
    @NonFinal
    View _root;
    String _modernPhrase;

    public ImageDialog(String modernPhrase) {
        _modernPhrase = modernPhrase;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _root = inflater.inflate(R.layout.image_dialog, null);
        new TaskRunner().executeAsync(new ImageLoader(CATAASAccessor.requestBuilder()
                        .text(_modernPhrase)
                        .type(CatType.ORIGINAL)
                        .textSize(30)
                        .build().toUrl()),
                (image) -> requireActivity().runOnUiThread(() -> onImageLoaded(image)));
        return _root;
    }

    private void onImageLoaded(Bitmap image) {
        ImageView imageView = _root.findViewById(R.id.imageView);
        imageView.setImageBitmap(image);
        _root.findViewById(R.id.imageLoadingProgressBar).setVisibility(View.GONE);
    }
}
