package sk.filipceko.golfscorecard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import io.realm.Realm;
import io.realm.RealmObject;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class ACreateEditDeleteFragment<T extends RealmObject> extends Fragment implements ICreateEditDeleteView<T> {

    protected static final String TO_EDIT_KEY = "scorecard_toEditElementId";

    protected View mainView;
    protected boolean isCreate = false;
    protected T itemToEdit;

    Set<OnResultListener<T>> listeners = new HashSet<>();

    protected abstract void onCreateViewLoad();
    protected abstract void save(View view);
    protected abstract T realmQuery(ObjectId objectId);
    protected abstract int getLayoutId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initializeFragment(savedInstanceState);
        mainView = inflater.inflate(getLayoutId(), container, false);
        MaterialButton saveButton = mainView.findViewById(R.id.save_button);
        if (saveButton != null){
            saveButton.setOnClickListener(this::save);
        }
        MaterialButton cancelButton = mainView.findViewById(R.id.cancel_button);
        if (cancelButton != null) {
            cancelButton.setOnClickListener(this::cancel);
        }
        MaterialButton deleteButton = mainView.findViewById(R.id.delete_button);
        if (deleteButton != null && !isCreate) {
            deleteButton.setOnClickListener(this::delete);
            deleteButton.setVisibility(View.VISIBLE);
        }
        onCreateViewLoad();
        return mainView;
    }

    protected void initializeFragment(Bundle savedInstance) {
        Bundle arguments = savedInstance;
        if (arguments == null) {
            arguments = getArguments();
        }
        if (arguments != null) {
            Serializable bundledItem = arguments.getSerializable(TO_EDIT_KEY);
            if (bundledItem != null) {
                itemToEdit = realmQuery((ObjectId) bundledItem);
            }
        }
        if (itemToEdit == null) {
            isCreate = true;
        }
    }

    protected void delete(View view) {
        //TODO Confirm
        if (itemToEdit.isManaged()) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            itemToEdit.deleteFromRealm();
            realm.commitTransaction();
        }
        fireDeleteEvent(itemToEdit);
        close(true);
    }

    protected void cancel(View view) {
        close(true);
    }

    protected void clearListeners() {
        listeners.clear();
    }

    @Override
    public void setOnResultListener(OnResultListener<T> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeOnResultListener(OnResultListener<T> listener) {
        listeners.remove(listener);
    }

    protected void fireSaveEvent(T savedObject){
        for (OnResultListener<T> listener : listeners) {
            listener.onSave(savedObject);
        }
    }

    protected void fireDeleteEvent(T savedObject){
        for (OnResultListener<T> listener : listeners) {
            listener.onDelete(savedObject);
        }
    }

    protected void fireCloseEvent() {
        for (OnResultListener<T> listener : listeners) {
            listener.onClose();
        }
    }

    protected void close(boolean popStack) {
        if (popStack) {
            getParentFragmentManager().popBackStack();
        }
        getParentFragmentManager().beginTransaction()
                .remove(this)
                .commit();
        fireCloseEvent();
    }
}
