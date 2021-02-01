package cxplugins.cxfundamental.minecraft.server

import java.util.ArrayList
import java.util.HashMap

import org.bukkit.Bukkit
import org.bukkit.configuration.serialization.ConfigurationSerializable
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector

/*fun Inventory.serialize():Map<String,Any>{
    val Result = HashMap<String, Any>()
    Result["InventoryType"] = this.type.toString()
    Result["Size"] = this.size
    Result["MaxStackSize"] = this.maxStackSize
    Result["Contents"] = this.contents
    Result["Title"] = this.title
    // TODO �Զ����ɵķ������
    return Result
}
fun getInventoryTypeFromString(type:String) :InventoryType{
    return InventoryType.valueOf(type)
}
fun Inventory.constructor(arg0:Map<String,Any>){

    var type= getInventoryTypeFromString(arg0["InventoryType"] as String)
    this=Bukkit.createInventory(null,type)
}*/
/**
 * Ϊ���ò����������ṩ���� ʹһ���������ܹ�ֱ�ӽ������л�
 * ���л�ʹ�÷���:var inventory=Bukkit.createInventory(null,9,"");
 * var cxinventory=CXInventory(inventory);
 * var config=CXYamlConfiguration("","Test.yml");
 * config.set("test",cxinventory);
 * cxinventory=config.get("test") as CXInventory;
 * inventory=cxinventory.toInventory();
 */

class CXInventory(var inventory: Inventory=Bukkit.createInventory(null,InventoryType.CHEST)) :ConfigurationSerializable,Inventory by inventory{

    /**
     *���л�
     *
     *
     */
    override fun serialize(): Map<String, Any?> {
        val result = HashMap<String, Any?>()
        result["InventoryType"] = inventory?.type.toString()
        result["Size"] = inventory?.size
        result["MaxStackSize"] = inventory?.maxStackSize
        result["Contents"] = this.contents
        result["Title"] = inventory?.title?:""
        //Result["Holders"]=inventory!!.holder
        // TODO �Զ����ɵķ������

        return result
    }



    /**
     * ����CXInventoryת��ΪInventory
     *
     * @return ת�����Inventory
     */
    fun toInventory(): Inventory? {
        return inventory
    }

    /*constructor(map:Map<String,Any?>) :this(){
        //println(1)
        var i= deserialize(map)
        this.inventory=i.inventory
    }*/
    companion object {
        /**
         *�����л�
         *
         *
         */
        @JvmStatic
        fun deserialize(map:Map<String,Any>) : CXInventory{
            //println(1)
            //println(map.toString())
            var type=InventoryType.valueOf(map["InventoryType"] as String)
            var inventory:Inventory=if(type==InventoryType.CHEST){
                var size=map["Size"] as Int
                Bukkit.createInventory(null,size,map["Title"] as? String)
            }
            else{
                Bukkit.createInventory(null,type,map["Title"] as? String)
            }
            var itemList:ArrayList<ItemStack> = map["Contents"]!! as ArrayList<ItemStack>
            inventory.contents= itemList.toTypedArray()


            return CXInventory(inventory)
        }
        /**
         * ����һ��Inventory
         *
         * @param IT ������������
         * @param Title �������ı���
         * @return ����������
         */
        fun create(IT: InventoryType, Title: String): Inventory {
            return Bukkit.createInventory(null, IT, Title)
        }
        /**
         * ����һ��Inventory
         *
         * @param Amount �������Ĵ�С
         * @param Title �������ı���
         * @return ����������
         */
        fun create(Amount: Int, Title: String): Inventory {
            return Bukkit.createInventory(null, Amount, Title)

        }
        /**
         * �� x y ����ת��Ϊ������λ��
         *
         * @param x x����
         * @param y y����
         * @return ������λ��
         */
        fun posToInteger(x: Int, y: Int): Int {
            return (y ) * 9 + (x )
        }
        /**
         * �� ������λ��ת��Ϊ x y ����
         * @param arg0 �����ڵ�λ��
         * @return ������λ�� Vector��X�����ʾ������X���� Y�����ʾ�����ڵ�Y����
         */
        fun integerToPos(arg0: Int): Vector {
            val V = Vector()
            V.setX(arg0 % 9)
            V.setY(arg0 / 9)
            return V
        }
        /**
         * ����һ�����е���������һ���µ�Inventory
         *
         * @param From ���е�����
         * @param Title �������ı���
         * @return ����������
         */
        fun create(From: Inventory, Title: String): Inventory {
            var Result = Bukkit.createInventory(null, From.type, Title)
            if (From.type == InventoryType.CHEST) Result = Bukkit.createInventory(null, From.size, Title)
            for (i in 0 until From.size) {
                if (From.getItem(i) != null) Result.setItem(i, From.getItem(i))
            }
            return Result
        }
    }
    /*class CXInventory : ConfigurationSerializable {

        internal var Inv: Inventory? = null

        /**
         *���л�
         *
         *
         */
        override fun serialize(): Map<String, Any> {
            val Result = HashMap<String, Any>()
            Result["InventoryType"] = Inv!!.type.toString()
            Result["Size"] = Inv!!.size
            Result["MaxStackSize"] = Inv!!.maxStackSize
            Result["Contents"] = Inv!!.contents
            Result["Title"] = Inv!!.title
            // TODO �Զ����ɵķ������

            return Result
        }
        /**
         *�����л�
         *
         *
         */
        constructor(arg0: Map<String, Any>) {
            Inv = Bukkit.createInventory(null, arg0["Size"] as Int, arg0["Title"] as String)
            val itemStacks = arg0["Contents"] as ArrayList<ItemStack>
            Inv!!.contents = itemStacks.toTypedArray()
            Inv!!.maxStackSize = arg0["MaxStackSize"] as Int
        }

        constructor() {
            return
        }

        /**
         * ����CXInventoryת��ΪInventory
         *
         * @return ת�����Inventory
         */
        fun toInventory(): Inventory? {
            return Inv
        }

        /**
         * ͨ��Inventory����һ��CXInventory
         */
        constructor(I: Inventory) {
            Inv = I

        }

        companion object {
            /**
             * ����һ��Inventory
             *
             * @param IT ������������
             * @param Title �������ı���
             * @return ����������
             */
            fun create(IT: InventoryType, Title: String): Inventory {
                return Bukkit.createInventory(null, IT, Title)
            }
            /**
             * ����һ��Inventory
             *
             * @param Amount �������Ĵ�С
             * @param Title �������ı���
             * @return ����������
             */
            fun create(Amount: Int, Title: String): Inventory {
                return Bukkit.createInventory(null, Amount, Title)

            }
            /**
             * �� x y ����ת��Ϊ������λ��
             *
             * @param x x����
             * @param y y����
             * @return ������λ��
             */
            fun posToInteger(x: Int, y: Int): Int {
                return (y - 1) * 9 + (x - 1)
            }
            /**
             * �� ������λ��ת��Ϊ x y ����
             * @param arg0 �����ڵ�λ��
             * @return ������λ�� Vector��X�����ʾ������X���� Y�����ʾ�����ڵ�Y����
             */
            fun integerToPos(arg0: Int): Vector {
                val V = Vector()
                V.setX(arg0 % 9 + 1)
                V.setY(arg0 / 9 + 1)
                return V
            }
            /**
             * ����һ�����е���������һ���µ�Inventory
             *
             * @param From ���е�����
             * @param Title �������ı���
             * @return ����������
             */
            fun create(From: Inventory, Title: String): Inventory {
                var Result = Bukkit.createInventory(null, From.type, Title)
                if (From.type == InventoryType.CHEST) Result = Bukkit.createInventory(null, From.size, Title)
                for (i in 0 until From.size) {
                    if (From.getItem(i) != null) Result.setItem(i, From.getItem(i))
                }
                return Result
            }
        }*/
    /*
	@Override
	public HashMap<Integer, ItemStack> addItem(ItemStack... arg0) throws IllegalArgumentException {
		List<ItemStack> ar=Arrays.asList(arg0);
		int i=0;
		for(ItemStack a:this.Contents.values()) {
			if(a==null) {
				a=ar.get(0);
				Contents.put(i, ar.get(0));
				ar.remove(0);
			}
			if(ar.size()<=0) break;
			i++;
		}
		// TODO �Զ����ɵķ������
		return Contents;
	}
	@Override
	public HashMap<Integer, ? extends ItemStack> all(int arg0) {
		HashMap<Integer,ItemStack> Result=new HashMap<Integer,ItemStack>();
		int i=0;
		for(ItemStack a:this.Contents.values()) {
			if(a.getTypeId()==arg0) {
				Result.put(i, a);
				i++;
			}
		}
		// TODO �Զ����ɵķ������
		return Result;
	}
	@Override
	public HashMap<Integer, ? extends ItemStack> all(Material arg0) throws IllegalArgumentException {
		HashMap<Integer,ItemStack> Result=new HashMap<Integer,ItemStack>();
		int i=0;
		for(ItemStack a:this.Contents.values()) {
			if(a.getType().equals(arg0)) {
				Result.put(i, a);
				i++;
			}
		}
		// TODO �Զ����ɵķ������
		return Result;
	}
	@Override
	public HashMap<Integer, ? extends ItemStack> all(ItemStack arg0) {
		HashMap<Integer,ItemStack> Result=new HashMap<Integer,ItemStack>();
		int i=0;
		for(ItemStack a:this.Contents.values()) {
			if(a.equals(arg0)) {
				Result.put(i, a);
				i++;
			}
		}
		// TODO �Զ����ɵķ������
		return Result;
	}
	@Override
	public void clear() {
		this.Contents=new HashMap<Integer,ItemStack>();
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void clear(int arg0) {
		int i=0;
		for(ItemStack a:Contents.values()) {
			if(a.getTypeId()==arg0) {
				Contents.put(i, null);
			}
			i++;
		}
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public boolean contains(int arg0) {
		for(ItemStack a:Contents.values()) {
			if(a.getTypeId()==arg0) {
				return true;
			}
			
		}
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean contains(Material arg0) throws IllegalArgumentException {
		for(ItemStack a:Contents.values()) {
			if(a.getType().equals(arg0)) {
				return true;
			}
			
		}
		// TODO �Զ����ɵķ������
		return false;
		
	}
	@Override
	public boolean contains(ItemStack arg0) {
		for(ItemStack a:Contents.values()) {
			if(a.equals(arg0)) {
				return true;
			}
			
		}
		// TODO �Զ����ɵķ������
		return false;
	}

	
	@Override
	public boolean contains(Material arg0, int arg1) throws IllegalArgumentException {
		for(ItemStack a:Contents.values()) {
			if(a.getType().equals(arg0)&&a.getAmount()==arg1) {
				return true;
			}
			
		}
		// TODO �Զ����ɵķ������
		return false;
		
	}
	@Override
	public boolean contains(ItemStack arg0, int arg1) {
		for(ItemStack a:Contents.values()) {
			if(CXItemStack.equalsIgnoreAmount(a, arg0)&&a.getAmount()==arg1) {
				return true;
			}
			
		}
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean containsAtLeast(ItemStack arg0, int arg1) {
		for(ItemStack a:Contents.values()) {
			if(CXItemStack.equalsIgnoreAmount(a, arg0)&&a.getAmount()>=arg1) {
				return true;
			}
			
		}
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public int first(int arg0) {
		int i=0;
		for(ItemStack a:Contents.values()) {
			if(a.getTypeId()==arg0) return i;
		}
		return -1;
	}
	@Override
	public int first(Material arg0) throws IllegalArgumentException {
		int i=0;
		for(ItemStack a:Contents.values()) {
			if(a.getType().equals(arg0)) return i;
		}
		return -1;
	}
	@Override
	public int first(ItemStack arg0) {
		int i=0;
		for(ItemStack a:Contents.values()) {
			if(a.equals(arg0)) return i;
		}
		return -1;
	}
	@Override
	public int firstEmpty() {
		int i=0;
		for(ItemStack a:Contents.values()) {
			if(a==null) return i;
		}
		return -1;
		
	}
	@Override
	public ItemStack[] getContents() {
		// TODO �Զ����ɵķ������
		
		return CXInventory.convertArray(ItemStack.class, this.Contents.values().toArray());
	}
	private static <T> T[] convertArray(Class<T> targetType, Object[] arrayObjects) {
        if (targetType == null) {
            return (T[]) arrayObjects;
        }
        if (arrayObjects == null) {
            return null;
        }
        T[] targetArray = (T[]) Array.newInstance(targetType, arrayObjects.length);
        try {
            System.arraycopy(arrayObjects, 0, targetArray, 0, arrayObjects.length);
        } catch (ArrayStoreException e) {
        	e.printStackTrace();
        }
        return targetArray;
    }
	@Override
	public ItemStack getItem(int arg0) {
		// TODO �Զ����ɵķ������
		return this.Contents.get(arg0);
	}
	@Override
	public int getMaxStackSize() {
		// TODO �Զ����ɵķ������
		return this.getMaxStackSize();
	}
	@Override
	public String getName() {
		// TODO �Զ����ɵķ������
		return this.toString();
	}
	@Override
	public int getSize() {
		// TODO �Զ����ɵķ������
		return this.Size;
	}
	@Override
	public String getTitle() {
		// TODO �Զ����ɵķ������
		return this.Title;
	}
	@Override
	public InventoryType getType() {
		// TODO �Զ����ɵķ������
		return this.Type;
	}
	@Override
	public List<HumanEntity> getViewers() {
		// TODO �Զ����ɵķ������
		return this.Viewers;
	}
	@Override
	public ListIterator<ItemStack> iterator() {
		// TODO �Զ����ɵķ������
		return this.Iter;
	}
	@Override
	public ListIterator<ItemStack> iterator(int arg0) {
		// TODO �Զ����ɵķ������
		return this.Iter;
	}
	@Override
	public void remove(int arg0) {
		int i=0;
		for(ItemStack a:this.Contents.values()) {
			if(a.getTypeId()==arg0) Contents.put(i, null);
			i++;
		}
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void remove(Material arg0) throws IllegalArgumentException {
		int i=0;
		for(ItemStack a:this.Contents.values()) {
			if(a.getType().equals(arg0)) Contents.put(i, null);
			i++;
		}
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void remove(ItemStack arg0) {
		int i=0;
		for(ItemStack a:this.Contents.values()) {
			if(a.equals(arg0)) Contents.put(i, null);
			i++;
		}
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public HashMap<Integer, ItemStack> removeItem(ItemStack... arg0) throws IllegalArgumentException {
		int i=0;
		List<ItemStack> Is=Arrays.asList(arg0);
		for(ItemStack a:this.Contents.values()) {
			if(a.equals(Is.get(0))) {
				Contents.put(i, null);
				Is.remove(0);
			}
			if(Is.size()<=0) return Contents;
			i++;
		}
		// TODO �Զ����ɵķ������
		return Contents;
	}
	@Override
	public void setContents(ItemStack[] arg0) throws IllegalArgumentException {
		for(int i=0;i<arg0.length;i++) {
			this.Contents.put(i,arg0[i]);
		}
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void setItem(int arg0, ItemStack arg1) {
		this.Contents.put(arg0, arg1);
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void setMaxStackSize(int arg0) {
		this.MaxStackSize=arg0;
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public boolean contains(int arg0, int arg1) {
		for(ItemStack a:Contents.values()) {
			if(a.getTypeId()==arg0&&a.getAmount()==arg1) {
				return true;
			}
			
		}
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public ItemStack getFuel() {
		// TODO �Զ����ɵķ������
		return this.Contents.get(1);
	}
	
	@Override
	public ItemStack getResult() {
		// TODO �Զ����ɵķ������
		return this.Contents.get(2);
	}
	@Override
	public ItemStack getSmelting() {
		// TODO �Զ����ɵķ������
		return this.Contents.get(0);
	}
	@Override
	public void setFuel(ItemStack arg0) {
		this.Contents.put(1, arg0);
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void setResult(ItemStack arg0) {
		this.Contents.put(2, arg0);
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void setSmelting(ItemStack arg0) {
		this.Contents.put(0, arg0);
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public ItemStack[] getMatrix() {
		// TODO �Զ����ɵķ������
		return null;
	}
	@Override
	public Recipe getRecipe() {
		// TODO �Զ����ɵķ������
		return null;
	}
	@Override
	public void setMatrix(ItemStack[] arg0) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public Furnace getHolder() {
		// TODO �Զ����ɵķ������
		return null;
	}
	*/

}
