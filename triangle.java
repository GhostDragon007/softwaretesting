package sjh;

public class triangle {
	public double a, b, c;
	public triangle(){
		this.a = 1;
		this.b = 1;
		this.c = 1;
	}
	public void setSide(double a, double b, double c){
		this.a = a;
		this.b = b;
		this.c = c;
		try{
			if(a>0&&b>0&&c>0&&a+b>c&&b+c>a&&a+c>b){			
				//nothing will happen
			}
			else{
				//not a triangle
				throw new Exception();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean ifEquilateral(){
		if(a == b && b == c){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean ifIsosceles(){
		if(a == b || b == c || a == c){
			return true;
		}
		else {
			return false;
		}
	}
	public boolean ifScalene(){
		if(a != b && b != c && a != c){
			return true;
		}
		else {
			return false;
		}
	}
}
