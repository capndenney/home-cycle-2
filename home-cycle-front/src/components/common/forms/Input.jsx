const Input = ({
  id,
  label,
  type,
  value,
  ref,
  required,
  handleChange,
  checked,
}) => {
  return (
    <div className="input-group">
      <label htmlFor={id}>
        {label}
        {required && "*"}
      </label>
      <input
        id={id}
        type={type || "text"}
        value={value}
        ref={ref}
        onChange={handleChange}
        checked={checked}
      />
    </div>
  );
};

export default Input;
