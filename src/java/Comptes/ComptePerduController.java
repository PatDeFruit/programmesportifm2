/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comptes;

import javax.inject.Named;

/**
 *
 * @author Asus
 */
@Named("comptePerdu")
public class ComptePerduController {
    private int cptMdp;
    
    
    //contructeur
    public ComptePerduController() {
       cptMdp =0;
    }

    
    //getter
    public int getCptMdp() {
        return cptMdp;
    }

    public void setCptMdp(int cptMdp) {
        this.cptMdp = cptMdp;
    }
    
}
