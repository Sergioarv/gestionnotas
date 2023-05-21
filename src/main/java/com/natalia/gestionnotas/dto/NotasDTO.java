package com.natalia.gestionnotas.dto;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 04/05/2023 - 14:41
 **/
public interface NotasDTO {

    int getIdnota();
    int getIdusuario();
    int getIdasignatura();
    String getNombre();
    String getApellido();
    String getCorreo();
    String getMateria();
    float getCalificacion();

}
