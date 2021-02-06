package com.alianza.api.model.exceptions;

/**
 * Error HTTP InternalServerErrorException
 * @author alopez
 *
 */
public class InternalServerErrorException extends ApiException {
	private static final long serialVersionUID = 1L;
    private String codigoError;
    
    public InternalServerErrorException(String msg,String codigoError, Exception ex){        
        super(msg,ex);
        this.codigoError=codigoError;
    }
    
    public InternalServerErrorException(String msg, Exception ex){
        super(msg,ex);
    }

    public InternalServerErrorException() {
        super();
    }

    public InternalServerErrorException(ErrorRq errorDto) {
        super(errorDto);
    }
    
    public String getCodigoError(){
        return codigoError;
    }

}
