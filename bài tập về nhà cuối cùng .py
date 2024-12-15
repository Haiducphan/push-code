import tkinter as tk
from tkinter import ttk, messagebox
import csv
import pandas as pd
from datetime import datetime

# File CSV lưu trữ dữ liệu
CSV_FILE = "employees.csv"

# Hàm tạo file CSV nếu chưa tồn tại
def create_csv_if_not_exists():
    try:
        with open(CSV_FILE, "x", newline="") as file:
            writer = csv.writer(file)
            writer.writerow(["Mã", "Tên", "Đơn vị", "Chức danh", "Ngày sinh", "Giới tính", "Số CMND", "Nơi cấp", "Ngày cấp"])
    except FileExistsError:
        pass

# Hàm lưu thông tin nhân viên vào CSV
def save_employee():
    data = [
        entry_id.get(), entry_name.get(), entry_department.get(), entry_title.get(),
        entry_birth.get(), gender_var.get(), entry_idnum.get(), entry_place.get(), entry_iddate.get()
    ]
    if "" in data:
        messagebox.showwarning("Cảnh báo", "Vui lòng điền đầy đủ thông tin!")
        return
    
    with open(CSV_FILE, "a", newline="") as file:
        writer = csv.writer(file)
        writer.writerow(data)
    messagebox.showinfo("Thành công", "Dữ liệu đã được lưu!")
    clear_entries()

# Hàm xóa các ô nhập liệu
def clear_entries():
    for entry in [entry_id, entry_name, entry_department, entry_title, entry_birth, entry_idnum, entry_place, entry_iddate]:
        entry.delete(0, tk.END)
    gender_var.set("")

# Hàm tìm nhân viên có sinh nhật hôm nay
def show_today_birthdays():
    try:
        df = pd.read_csv(CSV_FILE)
        today = datetime.now().strftime("%d/%m")
        birthdays = df[df["Ngày sinh"].str.startswith(today)]
        if not birthdays.empty:
            messagebox.showinfo("Sinh nhật hôm nay", birthdays.to_string(index=False))
        else:
            messagebox.showinfo("Thông báo", "Không có nhân viên nào sinh nhật hôm nay!")
    except Exception as e:
        messagebox.showerror("Lỗi", f"Không thể đọc dữ liệu: {e}")

# Hàm xuất danh sách nhân viên và sắp xếp theo tuổi giảm dần
def export_sorted_list():
    try:
        df = pd.read_csv(CSV_FILE)
        df["Ngày sinh"] = pd.to_datetime(df["Ngày sinh"], format="%d/%m/%Y", errors="coerce")
        df = df.sort_values(by="Ngày sinh", ascending=True)
        df["Ngày sinh"] = df["Ngày sinh"].dt.strftime("%d/%m/%Y")
        output_file = "sorted_employees.xlsx"
        df.to_excel(output_file, index=False, engine="openpyxl")
        messagebox.showinfo("Thành công", f"Danh sách đã được xuất ra file {output_file}")
    except Exception as e:
        messagebox.showerror("Lỗi", f"Không thể xuất file: {e}")

# Tạo giao diện
root = tk.Tk()
root.title("Quản lý thông tin nhân viên")

# Labels và Entries
tk.Label(root, text="Mã:").grid(row=0, column=0, padx=5, pady=5)
entry_id = tk.Entry(root)
entry_id.grid(row=0, column=1, padx=5, pady=5)

tk.Label(root, text="Tên:").grid(row=0, column=2, padx=5, pady=5)
entry_name = tk.Entry(root)
entry_name.grid(row=0, column=3, padx=5, pady=5)

tk.Label(root, text="Đơn vị:").grid(row=1, column=0, padx=5, pady=5)
entry_department = tk.Entry(root)
entry_department.grid(row=1, column=1, padx=5, pady=5)

tk.Label(root, text="Chức danh:").grid(row=1, column=2, padx=5, pady=5)
entry_title = tk.Entry(root)
entry_title.grid(row=1, column=3, padx=5, pady=5)

tk.Label(root, text="Ngày sinh (dd/mm/yyyy):").grid(row=2, column=0, padx=5, pady=5)
entry_birth = tk.Entry(root)
entry_birth.grid(row=2, column=1, padx=5, pady=5)

tk.Label(root, text="Giới tính:").grid(row=2, column=2, padx=5, pady=5)
gender_var = tk.StringVar()
ttk.Radiobutton(root, text="Nam", variable=gender_var, value="Nam").grid(row=2, column=3, sticky="w")
ttk.Radiobutton(root, text="Nữ", variable=gender_var, value="Nữ").grid(row=2, column=3, sticky="e")

tk.Label(root, text="Số CMND:").grid(row=3, column=0, padx=5, pady=5)
entry_idnum = tk.Entry(root)
entry_idnum.grid(row=3, column=1, padx=5, pady=5)

tk.Label(root, text="Nơi cấp:").grid(row=3, column=2, padx=5, pady=5)
entry_place = tk.Entry(root)
entry_place.grid(row=3, column=3, padx=5, pady=5)

tk.Label(root, text="Ngày cấp (dd/mm/yyyy):").grid(row=4, column=0, padx=5, pady=5)
entry_iddate = tk.Entry(root)
entry_iddate.grid(row=4, column=1, padx=5, pady=5)

# Buttons
tk.Button(root, text="Lưu thông tin", command=save_employee).grid(row=5, column=0, columnspan=2, pady=10)
tk.Button(root, text="Sinh nhật ngày hôm nay", command=show_today_birthdays).grid(row=5, column=2, columnspan=2, pady=10)
tk.Button(root, text="Xuất toàn bộ danh sách", command=export_sorted_list).grid(row=6, column=0, columnspan=4, pady=10)

# Tạo file CSV nếu chưa tồn tại
create_csv_if_not_exists()

# Chạy chương trình
root.mainloop()
