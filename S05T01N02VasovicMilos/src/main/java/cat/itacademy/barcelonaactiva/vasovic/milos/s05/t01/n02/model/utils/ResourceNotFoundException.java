package cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02.model.utils;

public class ResourceNotFoundException extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
