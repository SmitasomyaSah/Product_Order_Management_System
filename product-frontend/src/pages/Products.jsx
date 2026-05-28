import { useEffect, useState } from "react";
import axios from "../api/axios";

export default function Products() {
  const [products,setProducts]=useState([]);
  const [form,setForm]=useState({});

  const load=async()=>{
    const res=await axios.get("/products");
    setProducts(res.data);
  }

  const add=async()=>{
    await axios.post("/products",form);
    load();
  }

  const updateStatus=async(id,status)=>{
    await axios.patch(`/products/${id}/status?status=${status}`);
    load();
  }

  useEffect(()=>{load()},[])
   return (
    <div>
      <h2>Add Product</h2>
      <input placeholder="name" onChange={e=>setForm({...form,name:e.target.value})}/>
      <input placeholder="price" onChange={e=>setForm({...form,price:e.target.value})}/>
      <input placeholder="quantity" onChange={e=>setForm({...form,quantity:e.target.value})}/>
      <button onClick={add}>Add</button>

      <h2>Products</h2>
      {products.map(p=>(
        <div className="card" key={p.id}>
          {p.name} - {p.price} - {p.quantity} - {p.status}
          <button onClick={()=>updateStatus(p.id,'ENABLED')}>Enable</button>
          <button onClick={()=>updateStatus(p.id,'DISABLED')}>Disable</button>
        </div>
      ))}
    </div>
  );
}