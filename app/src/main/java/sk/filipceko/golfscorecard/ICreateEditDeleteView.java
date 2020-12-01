package sk.filipceko.golfscorecard;

public interface ICreateEditDeleteView<T> {
    void setOnResultListener(OnResultListener<T> listener);
    void removeOnResultListener(OnResultListener<T> listener);

    interface OnResultListener<T> {
        void onSave(T item);
        void onDelete(T item);
        void onClose();
    }
}
