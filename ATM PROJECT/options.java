import java.util.*;
import java.lang.*;
import java.io.*;
class Atm{
 String line="",strBal="";
       void SetData(String filepath,int acc_no,String acc_name,int pass,int bal)throws IOException{
          FileWriter details=new FileWriter(filepath,true);
          String s="\n"+acc_no+"          "+acc_name+"          "+pass+"                      $"+bal;
          details.write(s);
          details.write(10);
          details.flush();
          details.close();
       }
      boolean getAccountNo(String filepath,int acc_no){
       boolean flag=false;
       try{
           String line="";
               FileReader r=new FileReader(filepath);
               Scanner scan=new Scanner(r);
               while(scan.hasNextLine()){
                line=scan.nextLine();
               if(line.startsWith(acc_no+"")){
                  flag=true;
                  }
               }
               r.close();
             }
          catch(IOException exp){
               System.out.println("Your Atm is not allowed here");
               }
               if(flag)
                return true;
               else 
                return false;
     }
      void checkBalance(String filepath,int acc_no){
          try{
               FileReader r=new FileReader(filepath);
               Scanner scan=new Scanner(r);
               while(scan.hasNextLine()){
                line=scan.nextLine();
               if(line.startsWith(acc_no+"")){
                   System.out.println("Account_no          Account_name          Atm_passowrd          Account_balance");
                     System.out.println(line);}
               }
               r.close();
          }
          catch(IOException exp){
               System.out.println("Your Atm is not allowed here");
               } 
      }
   void deposit(String temppath,String filepath,int acc_no,int pass,int add){
          try{  
               File file=new File(filepath);
               File tm=new File(temppath);
               FileReader oldFile=new FileReader(filepath);
               FileWriter newFile=new FileWriter(temppath,true);
               int intBal=0;      
               Scanner sc=new Scanner(oldFile);
              while(sc.hasNextLine()){
                    line=sc.nextLine();
                    if(line.startsWith(acc_no+"")){
                         strBal=line.substring(line.indexOf("$")+1,line.length());
                         intBal=Integer.parseInt(strBal);
                          intBal+=add;                  
                         newFile.write("\n"+line.substring(0,line.indexOf("$")+1)+intBal);
                         }
                     else
                         newFile.write(line+"\n");
               }
               System.out.println("Your previous Balance:$"+strBal);
               System.out.println("                      +$"+add);
               System.out.println("Your Current  Balance:$"+intBal);
              //Files.copy(temp.toPath(),file.toPath());
               oldFile.close();
               newFile.close();
              file.delete();
              File f=new File(filepath);
              tm.renameTo(f);
          }   
          catch(IOException exp){
               System.out.println("Your Atm is not allowed here");
          }
     }
     void deleteAcc(String temppath,String filepath,int acc_no,int pass){
      try{  
               File file=new File(filepath);
               File tm=new File(temppath);
               FileReader oldFile=new FileReader(filepath);
               FileWriter newFile=new FileWriter(temppath,true);
               int intBal=0;
               boolean flag=false;      
               Scanner sc=new Scanner(oldFile);
               while(sc.hasNextLine()){
                    line=sc.nextLine();
                if(line.startsWith(acc_no+"")){
                 newFile.write("");
                     }
               else
                    newFile.write(line+"\n");
               }
             //Files.copy(temp.toPath(),file.toPath());
               oldFile.close();
               newFile.close();
              file.delete();
              File f=new File(filepath);
              tm.renameTo(f);
          }
          catch(IOException exp){
               System.out.println("Your Atm is not allowed here");
          }            
     }
   void withdraw(String temppath,String filepath,int acc_no,int pass,int add){
          try{  
               File file=new File(filepath);
               File tm=new File(temppath);
               FileReader oldFile=new FileReader(filepath);
               FileWriter newFile=new FileWriter(temppath,true);
               int intBal=0;
               boolean flag=false;      
               Scanner sc=new Scanner(oldFile);
               while(sc.hasNextLine()){
                    line=sc.nextLine();
                if(line.startsWith(acc_no+"")){
                    strBal=line.substring(line.indexOf("$")+1,line.length());
                    intBal=Integer.parseInt(strBal);
                                      
                    if(intBal>add){
                      intBal-=add;
                 newFile.write("\n"+line.substring(0,line.indexOf("$")+1)+intBal);
                     }
         
                  else{
                    flag=true;
                    newFile.write(line+"\n"); 
                  }
               }
               else
                    newFile.write(line+"\n");
               }
               if(flag)
                System.out.println("You entered bigger amount then balance");
               else{
          System.out.println("Your previous Balance:$"+strBal);
               System.out.println("                      -$"+add);
               System.out.println("Your Current  Balance:$"+intBal);}
              //Files.copy(temp.toPath(),file.toPath());
               oldFile.close();
               newFile.close();
              file.delete();
              File f=new File(filepath);
              tm.renameTo(f);
              }
          catch(IOException exp){
               System.out.println("Your Atm is not allowed here");
          }            
     }
}  
class Sbi extends Atm{
     public Sbi(){}
   } 
class Boi extends Atm{
   String line="",strBal="";
     public Boi(){}
   }
class Pnb extends Atm{
     public Pnb(){}
}
class Yesbank extends Atm{
     public Yesbank(){}
}

class Sample{
	static Scanner sc=new Scanner(System.in);
	static void loop(int n,String bank_name,int acc_no,int pass,String filepath)throws Exception{
		int choice,dep,add;
		String temppath="temp"+bank_name+".txt";
		System.out.println("Type 1. View Balance\nType 2. Withdraw Balance\nType 3. Deposit Balance\nType 4. Delete Account \nType 5. Exit");
		System.out.print("Enter your choice:");
		choice=sc.nextInt();
		Class c=Class.forName(bank_name);
        Object r=c.newInstance();
        if(r instanceof Atm){
        	Atm card=(Atm)r;
		switch(choice){
			case 1:
                card.checkBalance(filepath,acc_no);
				break;
			case 2:
				System.out.print("what you withdraw in balance:$");
            	dep=sc.nextInt();
				card.withdraw(temppath,filepath,acc_no,pass,dep);
				break;
			case 3:
				System.out.print("what you deposit in balance:$");
				add=sc.nextInt();
				card.deposit(temppath,filepath,acc_no,pass,add);
        br
      case 4:
        card.deleteAcc(temppath,filepath,acc_no,pass);
        break;
      case 5:
        break;
			default:
			System.out.println("invalid Input");
		}
		if(choice!=4&&choice!=5)
			loop(choice,bank_name,acc_no,pass,filepath);
		else 
			System.out.println("Exit");
		}
	}
	public static void main(String[] args) throws Exception{
		int acc_no,bal,pass,bank_choice,newpass,dep,add;
		String resetpass,bank_name,acc_name,filepath="";
		boolean flag=true;
		System.out.println("Which bank you have Account: Boi/Pnb/Sbi/Yesbank/Type \"No\" if you don't have account");
		bank_name=sc.nextLine();
    filepath=bank_name+".txt";
		if(bank_name.equals("No")){
			System.out.println("In Which bank you want to open account:\nTyepe 1 Bank of India\nType 2 State bank of India\nType 3 Punjab national Bank\nType 4 Yesbank\nEnter your choice:");
			bank_choice=sc.nextInt();
			if(bank_choice>4){
				System.out.println("Please Enter a Valid Bank:");
				System.exit(1);
			}
			Random ran=new Random();
               System.out.println("We Generated Your Acount Number:");
               acc_no=ran.nextInt(1000000000);
               System.out.println("Your Acount Number is:"+acc_no);
               System.out.print("Please Enter Your Full Name:");
               sc.nextLine();
               acc_name=sc.nextLine();
			System.out.print("Create First time Your AtmCode(don't Share with any one):");
			pass=sc.nextInt();
			System.out.print("What you deposit in your account:$");
			bal=sc.nextInt();
			if(bank_choice==1){
				bank_name="Boi";
        filepath=bank_name+".txt";
        Atm a=new Boi();
			   a.SetData(filepath,acc_no,acc_name,pass,bal);
			}
			if(bank_choice==2){
				bank_name="Sbi";
        filepath=bank_name+".txt";
        Atm a=new Sbi();
			a.SetData(filepath,acc_no,acc_name,pass,bal);
			}
			if(bank_choice==3){
				bank_name="Pnb";
        filepath=bank_name+".txt";
        Atm a =new Pnb();
				a.SetData(filepath,acc_no,acc_name,pass,bal);
			}
			if(bank_choice==4){
				bank_name="Yesbank";
        filepath=bank_name+".txt";
        Atm a=new Yesbank();
				a.SetData(filepath,acc_no,acc_name,pass,bal);
			}

		}
		else{
			System.out.print("Your accountno.:");
			acc_no=sc.nextInt();
			System.out.print("Enter Your new AtmCode(don't Share with any one):");
			pass=sc.nextInt();

			if(bank_name.equals("Boi")){
        Atm a=new Boi();
				if(!(a.getAccountNo(filepath,acc_no))) {
					flag=false;
					System.out.println("Invalid AccountNo");                                     
				}
			}
			else if(bank_name.equals("Sbi")){
        Atm a=new Sbi();
				if(!(a.getAccountNo(filepath,acc_no))){
					flag=false;
					System.out.println("Invalid AccountNo");
				}
			}
			else if(bank_name.equals("Pnb")){
        Atm a=new Pnb();
				if(!(a.getAccountNo(filepath,acc_no))) {
					flag=false;
					System.out.println("Invalid AccountNo");
				}
			}
			else if(bank_name.equals("Yesbank")){
        Atm a=new Yesbank();
				if(!(a.getAccountNo(filepath,acc_no))) {
					flag=false;
					System.out.println("Invalid AccountNo");
				}
			}
			else{
				System.out.println("Your Bank is not connect with us:");
				System.exit(1);
			}
		}
		System.out.println("What oparation you perform with your account:");
		if(flag)
			loop(0,bank_name,acc_no,pass,filepath);

	System.out.println("==========================================THANK YOU FOR USING===========================================");
	}
}