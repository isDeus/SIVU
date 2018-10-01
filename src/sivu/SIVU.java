/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sivu;

import java.util.*;

/**
 *
 * @author Mauricio
 */
public class SIVU {
    public ArrayList<Producto> productos;
    public ArrayList<Venta> ventas;
    public ArrayList<Usuario> usuarios;
    private int contador;

    public SIVU(){ 
        this.productos =  new ArrayList<Producto>();
        this.ventas = new ArrayList<Venta>();
        this.contador = 1;
    }
    
    public void menuUsuario(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido a el Sistema de Inventario y Ventas Automatizado S.I.V.U");
        System.out.println("Eliga que accion desea realizar:");
        System.out.println("1)Realizar una venta");
        System.out.println("2)Mostrar el inventario");
        System.out.println("3)Buscar producto");        
        System.out.println("4)Cerrar sesión");
        int seleccion = sc.nextInt();
        seleccionUsuario(seleccion);
    }
    //Jonathan: Se puede hacer un switch para el metodo seleccionUsuario, y crear metodos faltantes
    //te dejé el case 1 vacio por que ese era el tuyo uwu
    public void seleccionUsuario(int seleccion){
        switch(seleccion){
            case 1:
                break;
            case 2:
                imprimirInventario();
                break;
            case 3:
                int codigo = preguntarInt("codigo","producto");
                buscarProducto(codigo);
                break;
            case 4:
                logout();
                break;
            default:
                System.out.println("La elección es incorrecta, intente denuevo.");
                menuUsuario();
                break;
        }
    }
    
    public void menuAdmin(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido a el Sistema de Inventario y Ventas Automatizado S.I.V.U");
        System.out.println("Eliga que accion desea realizar:");
        System.out.println("1)Crear Usuario");
        System.out.println("2)Mostrar el inventario");
        System.out.println("3)Buscar producto");
        System.out.println("4)Aumentar o rebajar el precio de un producto");
        System.out.println("5)Añadir o Disminuir el Stock de un producto");
        System.out.println("6)Cerrar sesión");
        int seleccion = sc.nextInt();
        seleccionAdmin(seleccion);
    }
    //Jonathan: Lo mismo que en el caso del usuario
    public void seleccionAdmin(int seleccion){
        switch(seleccion){
            case 1:
                String nombre = preguntarString("nombre", "usuario");
                String contraseña = preguntarString("password", "usuario");
                crearUsuario(nombre,contraseña);
                break;
            case 2:
                imprimirInventario();
                break;
            case 3:
                int codigoBusqueda = preguntarInt("codigo","producto");
                buscarProducto(codigoBusqueda);
                break;
            case 4:
                int codigoValor = preguntarInt("codigo","producto");
                int valor = preguntarInt("valor","aumento o la rebaja");
                sumarPrecio(codigoValor,valor);
                break;
            case 5:
                int codigoStock = preguntarInt("codigo","producto");
                int stock = preguntarInt("valor","stock");
                sumarStock(codigoStock,stock);
                break;
            case 6:
                logout();
                break;
            default:
                System.out.println("La elección es incorrecta, intente denuevo.");
                menuAdmin();
                break;
        }
    }
    
    private  void añadirProducto(String nombre, int precio, int stock){
        this.productos.add(new Producto(nombre,precio,stock,this.contador));
        this.contador++;
    }
    
    private String preguntarString(String palabra, String palabra2){
        Scanner sc = new Scanner(System.in);
        System.out.println("Porfavor ingrese el "+palabra+" del "+palabra2);
        String word = sc.nextLine();
        return word;
    }
    
    private int preguntarInt(String palabra, String palabra2){
        Scanner sc = new Scanner(System.in);
        System.out.println("Porfavor ingrese el "+palabra+" del "+palabra2);
        int valor = sc.nextInt();
        return valor;
    }
    
    public void sumarStock(int codigo, int valor){
        for(Producto item:this.productos){
            if(codigo == (item.getCodigo())){
                item.sumarStock(valor);
            }
        }
    }
    
    public void sumarPrecio(int codigo, int valor){
        for(Producto item:this.productos){
            if(codigo == (item.getCodigo())){
                item.sumarPrecio(valor);
            }
        }
    }
    
    public String toString() {
        String s="";
        s+="El inventario contiene:\n";
        for (Producto item:this.productos) {
            s+='\n'+item.toString();
        }
        return s;
    }
    
    public void imprimirInventario(){
        System.out.println(toString());
    }
    /* Jonathan: cambie un poco el metodo vender. en vez de buscar
    por el nombre, que busque por el codigo del producto. Además, separé el metodo
    y cree un "mostrarVender" para que muestre el precioTotal */
    public void vender(int codigo, int cantidad){
        int precioTotal;
       
        for(Producto item:this.productos){
            if(codigo == item.getCodigo()){
                item.sumarStock(-cantidad);
                precioTotal = item.getPrecio()*cantidad;
                codigo = item.getCodigo();
                this.ventas.add(new Venta(precioTotal, codigo, cantidad)); 
                mostrarVender(precioTotal);
            }
        }
    }
    
    private void mostrarVender(int precioTotal){
        System.out.println("El valor total de la venta es de : "+(precioTotal));
    }
    
    /* Jonathan: Cree el metodo getVentas para poder trabajar con el array 
    en las demas clases */
    public ArrayList<Venta> getVentas(){
        return this.ventas;
    }
    // Jonathan: Lo mismo que lo anterior
    public ArrayList<Producto> getProductos(){
        return this.productos;
    }
    
    //hola soy mauricio
    public void buscarProducto(int codigo){
        for(Producto item:this.productos){
            if(codigo == item.getCodigo()){
                System.out.println("Se ha encontrado el item con el código : "+codigo);
                item.toString();
            }
        }
    }
    
    //Metodo que se hace al inicio para ver el tema de los usuarios
    public void eleccionUsuario(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Que tipo de usuario va a usar S.I.V.U?");
        System.out.println("1)Administrador");
        System.out.println("2)Vendedor");
        int seleccion = sc.nextInt();
        switch(seleccion){
            case 1:
                menuAdmin();
                break;
            case 2:
                menuUsuario();
                break;
            default:
                System.out.println("Eleccion invalida, intente denuevo");
                eleccionUsuario();
                break;
        }
    }
    
    public void crearUsuario(String nombre, String contraseña){
        this.usuarios.add(new Usuario(nombre, contraseña));
    }
    
    public void logout(){
    }
}
