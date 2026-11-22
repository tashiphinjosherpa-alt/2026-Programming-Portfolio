import json


class Account:
    def __init__(self, acc_no, name, balance):
        self.acc_no = acc_no
        self.name = name
        self.balance = balance

    def deposit(self, amount):
        self.balance += amount
        print(f"Rs.{amount} deposited successfully.")

    def withdraw(self, amount):
        if amount > self.balance:
            print("Insufficient Balance!")
        else:
            self.balance -= amount
            print(f"Rs.{amount} withdrawn successfully.")

    def display(self):
        print("\n----- Account Details -----")
        print("Account No :", self.acc_no)
        print("Name       :", self.name)
        print("Balance    :", self.balance)


class SavingsAccount(Account):
    def __init__(self, acc_no, name, balance):
        super().__init__(acc_no, name, balance)

    def display(self):
        print("\n[Savings Account]")
        super().display()


accounts = []


def save_data():
    data = []

    for acc in accounts:
        data.append({
            "acc_no": acc.acc_no,
            "name": acc.name,
            "balance": acc.balance
        })

    with open("bank_data.json", "w") as file:
        json.dump(data, file, indent=4)


def load_data():
    try:
        with open("bank_data.json", "r") as file:
            data = json.load(file)

            for item in data:
                accounts.append(
                    SavingsAccount(
                        item["acc_no"],
                        item["name"],
                        item["balance"]
                    )
                )
    except:
        pass


def create_account():
    acc_no = input("Enter Account Number: ")
    name = input("Enter Name: ")

    try:
        balance = float(input("Enter Initial Balance: "))
    except:
        print("Invalid Input!")
        return

    account = SavingsAccount(acc_no, name, balance)
    accounts.append(account)

    print("Account Created Successfully!")


def find_account(acc_no):
    for acc in accounts:
        if acc.acc_no == acc_no:
            return acc
    return None


def deposit_money():
    acc_no = input("Enter Account Number: ")

    acc = find_account(acc_no)

    if acc:
        amount = float(input("Enter Amount: "))
        acc.deposit(amount)
    else:
        print("Account Not Found!")


def withdraw_money():
    acc_no = input("Enter Account Number: ")

    acc = find_account(acc_no)

    if acc:
        amount = float(input("Enter Amount: "))
        acc.withdraw(amount)
    else:
        print("Account Not Found!")


def view_accounts():
    if not accounts:
        print("No Accounts Found!")
        return

    for acc in accounts:
        acc.display()


def search_account():
    acc_no = input("Enter Account Number: ")

    acc = find_account(acc_no)

    if acc:
        acc.display()
    else:
        print("Account Not Found!")


def delete_account():
    acc_no = input("Enter Account Number: ")

    acc = find_account(acc_no)

    if acc:
        accounts.remove(acc)
        print("Account Deleted Successfully!")
    else:
        print("Account Not Found!")


load_data()

while True:

    print("\n===== BANK MANAGEMENT SYSTEM =====")
    print("1. Create Account")
    print("2. Deposit Money")
    print("3. Withdraw Money")
    print("4. View All Accounts")
    print("5. Search Account")
    print("6. Delete Account")
    print("7. Save & Exit")

    choice = input("Enter Choice: ")

    if choice == "1":
        create_account()

    elif choice == "2":
        deposit_money()

    elif choice == "3":
        withdraw_money()

    elif choice == "4":
        view_accounts()

    elif choice == "5":
        search_account()

    elif choice == "6":
        delete_account()

    elif choice == "7":
        save_data()
        print("Data Saved Successfully!")
        print("Thank You!")
        break

    else:
        print("Invalid Choice!")