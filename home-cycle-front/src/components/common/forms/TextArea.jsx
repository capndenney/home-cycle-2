const TextArea = ({
  id,
  label,
  rows,
  cols,
  value,
  ref,
  required,
  handleChange,
}) => {
  return (
    <div className="input-group">
      <label htmlFor={id}>
        {label}
        {required && "*"}
      </label>
      <textarea
        id={id}
        rows={rows || "4"}
        cols={cols || "35"}
        value={value}
        ref={ref}
        onChange={handleChange}
      ></textarea>
    </div>
  );
};

export default TextArea;
