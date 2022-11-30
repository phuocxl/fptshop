package fa.training.model;

public class DataRespose {

    private String error;
    private String message;
    private Object data;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DataRespose() {
    }

    public DataRespose(String error, String message, Object data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public DataRespose(Object data) {
        this.data = data;
        this.message ="success";
    }

    public DataRespose(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
