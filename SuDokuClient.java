import java.rmi.*;
import java.rmi.server.*;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.rmi.registry.*;
     
    public class SuDokuClient {
  
        public static void main (String[ ] args) {  
     if (System.getSecurityManager() == null) {
         System.setSecurityManager(new RMISecurityManager());
         }
             
             int xx=1;
             Scanner scan = new Scanner(System.in);
             Scanner scann = new Scanner(System.in);
           // String url = "//localhost:1099/";
          
            try {   
               //connect to the remote object via the registry          
               // SuDokuGame s =  (SuDokuGame)Naming.lookup(url + "SuDoku");
	 Registry reg = LocateRegistry.getRegistry("localhost",1099);
 	 FabSudokuInterface fabrique =(FabSudokuInterface)
         reg.lookup("Fabrique");
         SuDokuGame Obj;
                Obj=(SuDokuGame) fabrique.newSudokuImp();
     
                //SuDokuGame s=(SuDokuGame)Naming.lookup("rmi://localhost:1099/SuDoku");
                int[][] grid ;
                 
                    while (xx==1)
         {      grid =Obj.getGameGrid ();
                String S=Obj.affichage(grid) ; 

                System.out.println(S);
		System.out.println("1: initialiser la sudoku  \n 2 : ajouter un numero  \n 3: voir la solution  " );
                int var = scan.nextInt();
                if(var==1)
                {
                System.out.print("la sudoku initiale est : \n ");
                Obj.init();
                //grid =Obj.getGameGrid ();
                }
                if (var==2)
                {
               
                System.out.print("donner la colonne ou tu veux ajouter le numero: \n ");
                int c = scan.nextInt();
                System.out.print("donner la ligne ou tu veux ajouter le numero: \n ");
                int l = scan.nextInt();
   
                System.out.print("donner le numero :\n ");
              
                while (!scan.hasNextInt()) scan.next();
                int num = scan.nextInt();
                System.out.print("Enter number : ");
                if (num >0 && num <10 ) {    
                if (Obj.addNumber(num,l,c))
                    System.out.println("le numero est ajouter avec succée");
                    else 
                    System.out.println("le numero Existe déja ");
                }
                else
                 System.out.println("le numero que tu as entrer n'est pas valide");
                 }
                if (var==3)
                {
                  System.out.print("la solution est: \n");
                for (int f=0;f<82;f=f+1)
               {
		Obj.estValide(grid,f);}
 
                }             

  }

           } catch (Exception e) {System.out.println ("Erreur d'acces a l'objet distant.");
		System.out.println (e.toString()); }
  
            System.exit(0);
            
  
        } // end main
         
    } // end SuDokuClient
     
