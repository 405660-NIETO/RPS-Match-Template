package ar.edu.utn.frc.tup.lciii.videos.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

//generar la anotacion
@Documented
//indica que la anotacion es un Jakarta Bean Validation Constraint
@Constraint(validatedBy = PasswordConstraintValidator.class)
//Donde se va a utilizar
@Target({ TYPE, FIELD, ANNOTATION_TYPE})
//Retention quiere decir que es que se va a ejecutar en tiempo de ejecucion
//No es lo mismo a tiempo de desarrollo o compilacion
@Retention(RUNTIME)
//agrego el arroba en la interfaz, el nombre de la anotacion es el nombre de la interfaz
public @interface ValidPassword {
    //retorna un mensaje default que es el siguiente si nadie lo sobreescribe
    String message() default "Invalid Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
