import { useState } from "react";
import axios from "../api/axios";
import { useNavigate } from "react-router-dom";

export default function Register() {
  const [form, setForm] = useState({});
  const navigate = useNavigate();

  const register = async () => {
    await axios.post("/auth/register", form);
    navigate("/login");
  };

  return (
    <div className="card">
      <h2>Register</h2>
      <input placeholder="Name" onChange={e => setForm({...form,name:e.target.value})} />
      <input placeholder="Email" onChange={e => setForm({...form,email:e.target.value})} />
      <input placeholder="Password" type="password" onChange={e => setForm({...form,password:e.target.value})} />
      <input placeholder="Address" onChange={e => setForm({...form,address:e.target.value})} />
      <button onClick={register}>Register</button>
    </div>
  );
}
