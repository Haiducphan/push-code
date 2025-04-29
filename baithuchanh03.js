class SanPham {
    #id;
    #ten;
    #gia;
    #soLuong;

    constructor(id, ten, gia, soLuong) {
        this.#id = id;
        this.#ten = ten;
        this.#gia = gia;
        this.#soLuong = soLuong;
    }
    get id() {
        return this.#id;
    }
    get ten() {
        return this.#ten;
    }
    set ten(ten) {
        this.#ten = ten;
    }
    get gia() {
        return this.#gia;
    }
    set gia(gia) {
        this.#gia = gia;
    }
    get soLuong() {
        return this.#soLuong;
    }
    set soLuong(soLuong) {
        this.#soLuong = soLuong;
    }
}

class CuaHang {
    #mapSanPham;
    #dem;
    constructor() {
        this.#mapSanPham = new Map([
            ["00001", new SanPham("00001", "Sữa tươi", 5000, 2)],
        ]);
        this.#dem = 1;
    }

    tongSoLoaiSanPham() {
        return this.#mapSanPham.size;
    }

    tongSoLuongSanPham() {
        let tongSoLuong = 0;
        for (const id of this.#mapSanPham.keys()) {
            const sanPham = this.#mapSanPham.get(id);
            tongSoLuong += sanPham.soLuong;
        }
        return tongSoLuong;
    }

    tongGiaTien() {
        let tongGia = 0;
        for (const sanPham of this.#mapSanPham.values()) {
            tongGia += sanPham.gia * sanPham.soLuong;
        }
        return tongGia;
    }

    layDanhSachSanPham() {
        const ketQua = [];
        for (const sanPham of this.#mapSanPham.values()) {
            ketQua.push(sanPham);
        }
        return ketQua;
    }

    #taoId() {
        return (++this.#dem).toString().padStart(5, "0");
    }

    taoSanPham(ten, gia, soLuong) {
        const id = this.#taoId();
        this.#mapSanPham.set(id, new SanPham(id, ten, gia, soLuong));
    }

    suaSanPham(id, ten, gia, soLuong) {
        if (this.#mapSanPham.has(id)) {
            const sanPham = this.#mapSanPham.get(id);
            sanPham.ten = ten;
            sanPham.gia = gia;
            sanPham.soLuong = soLuong;
        }
    }

    xoaSanPham(id) {
        this.#mapSanPham.delete(id);
    }

}
const cuaHang = new CuaHang();


function themMoiSanPham() {
    const ten = prompt("Nhập tên sản phẩm");
    const gia = parseInt(prompt("Nhập giá tiền"));
    const soLuong = parseInt(prompt("Nhập số lượng"));
    cuaHang.taoSanPham(ten, gia, soLuong);
    hienThi();
}

function suaSanPham(id) {
    const sanPham = cuaHang.layDanhSachSanPham().find(sp => sp.id === id);
    if (sanPham) {
        const ten = prompt("Nhập tên sản phẩm mới", sanPham.ten);
        const gia = parseInt(prompt("Nhập giá tiền mới", sanPham.gia));
        const soLuong = parseInt(prompt("Nhập số lượng mới", sanPham.soLuong));
        cuaHang.suaSanPham(id, ten, gia, soLuong);
        hienThi();
    }
}
function xoaSanPham(id) {
    cuaHang.xoaSanPham(id);
    hienThi();
}


function hienThiThongKe() {
    tongSoLoaiSanPham.textContent =
        `Tổng số loại sản phẩm: ${cuaHang.tongSoLoaiSanPham()}`
    tongSoLuong.textContent =
        `Tổng số lượng sản phẩm: ${cuaHang.tongSoLuongSanPham()}`
    tongGiaTien.textContent = 
        `Tổng giá tiền: ${cuaHang.tongGiaTien()} VNĐ`;

}
function hienThiSanPham() {
    danhSachSanPham.innerHTML = "";
    for (const sanPham of cuaHang.layDanhSachSanPham()) {
        danhSachSanPham.innerHTML += `
            <li>
                ${sanPham.id} | ${sanPham.ten} | ${sanPham.gia} VNĐ | ${sanPham.soLuong} cái
                <button onclick="suaSanPham('${sanPham.id}')">Sửa</button>
                <button onclick="xoaSanPham('${sanPham.id}')">Xoá</button>
            </li>

        `;
    }
}
function hienThi() {
    hienThiThongKe();
    hienThiSanPham();
}
hienThi();    


