package com.unicaes.poo.infra.exceptions;



/*   COMO USARLO?
* Para usar esta exception necesitan utilizarme en TryCatch y
* utilizar throw new QueryException dentro del catch
*
* Ejemplos claros son los metodos de ProductService para ver como funciona
*
* */



public class QueryException extends RuntimeException {

    public QueryException(String message) {
        super(message);
    }
}
