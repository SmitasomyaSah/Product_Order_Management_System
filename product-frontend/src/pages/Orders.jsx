import { useEffect,useState } from "react";
import axios from "../api/axios";

export default function Orders(){
  const [orders,setOrders]=useState([]);
  const userId=1;

  const load=async()=>{
    const res=await axios.get("/orders");
    setOrders(res.data);
  }

  const place=async()=>{
    await axios.post(`/orders?userId=${userId}`);
    load();
  }

  useEffect(()=>{load()},[])
   return(
    <div>
      <button onClick={place}>Place Order</button>

      {orders.map(o=>(
        <div className="card" key={o.orderId}>
          Order #{o.orderId} - {o.totalAmount}
        </div>
      ))}
    </div>
  )
}
