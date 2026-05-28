import { useEffect,useState } from "react";
import axios from "../api/axios";

export default function Cart(){
  const [cart,setCart]=useState(null);
  const [form,setForm]=useState({});
  const userId=1;

  const load=async()=>{
    const res=await axios.get(`/cart?userId=${userId}`);
    setCart(res.data);
  }

  const add=async()=>{
    await axios.post(`/cart/items?userId=${userId}`,form);
    load();
  }

  const update=async(id)=>{
    await axios.put(`/cart/items/${id}`,{quantity:form.quantity});
    load();
  }
  const remove=async(id)=>{
    await axios.delete(`/cart/items/${id}`);
    load();
  }

  useEffect(()=>{load()},[])

  return(
    <div>
      <h2>Add to Cart</h2>
      <input placeholder="productId" onChange={e=>setForm({...form,productId:e.target.value})}/>
      <input placeholder="quantity" onChange={e=>setForm({...form,quantity:e.target.value})}/>
      <button onClick={add}>Add</button>

      {cart && cart.items?.map(i=>(
        <div className="card" key={i.cartItemId}>
          {i.productName} - {i.quantity}
          <button onClick={()=>update(i.cartItemId)}>Update</button>
          <button onClick={()=>remove(i.cartItemId)}>Delete</button>
        </div>
      ))}

      <h3>Total: {cart?.totalAmount}</h3>
    </div>
  )
}
