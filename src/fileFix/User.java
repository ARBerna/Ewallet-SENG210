package src.fileFix;
import java.util.ArrayList;

public class User {
	private ArrayList <Currency>currencyRates = new ArrayList<>();
	private ArrayList <Wage>Income            = new ArrayList<>();     // user income sources that user can record or view or search by type or month
	private ArrayList <Expense>Spending       = new ArrayList<>();  //user's expenses
	int userID;
	String username;
	String pwd;
	//current total income - total
	double balance;
	// possible monthly savings, calculated using monthly income (most recent) assuming the data we have is for one year, and monthly and biweekly expenses, here you can assume yearly expenses that are recorded have already been paid.
	double monthlysavings;
	//should add constructor(s)

	public User(int userID, String username, String passwordHash) {
		// TODO Auto-generated constructor stub
		this.userID = userID;
		this.username = username;
		this.pwd = passwordHash;
	}

	//get the most recently input wage
	public Wage getRecentWage() {
		if (Income.size() > 0) {
			return Income.get(0);
		}
		else {
			return new Wage("", 0.0, "");
		}
	}

	//get the list of wages
	public ArrayList<Wage> getWages() {
		return Income;
	}

	//get the list of expenses
	public ArrayList<Expense> getExpenses() {
		return Spending;
	}

	//adds wage to incomes
	public void addWage(Wage w) {
		Income.add(0, w);
	}

	//adds expense to spending
	public void addExpense(Expense e) {
		Spending.add(e);
	}

	public String getPassword() {
		return this.pwd;
	}
}