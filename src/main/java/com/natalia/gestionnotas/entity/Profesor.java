package com.natalia.gestionnotas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

/**
 * @Project gestionnotas
 * @Author Sergio Abelardo Rodríguez Vásquez
 * @Email ingsergiorodriguezv@gmail.com
 * @Date 28/04/2023 - 12:08
 **/

@Entity
@PrimaryKeyJoinColumn(name = "idusuario")
public class Profesor extends Usuario{
}
