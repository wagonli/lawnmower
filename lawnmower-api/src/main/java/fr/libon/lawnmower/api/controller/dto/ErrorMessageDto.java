package fr.libon.lawnmower.api.controller.dto;

public class ErrorMessageDto {
    private int status;
    private String title;
    private String detail;

    public ErrorMessageDto(int status,String title, String detail) {
        this.status = status;
        this.title = title;
        this.detail = detail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
