"use client";
import { useEffect, useState } from "react";

const CreateImageQuizContainer = () => {
  const [position, setPosition] = useState({ x: 0, y: 0 });
  const [elemParams, setElemParams] = useState({ width: 250, height: 150 });
  const [position2, setPosition2] = useState({
    x: elemParams.width,
    y: elemParams.height,
  });

  const [disabled, setDisabled] = useState(false);
  const [mousePosition, setMousePosition] = useState({ x: 0, y: 0 });

  useEffect(() => {
    setElemParams({
      width: position2.x - position.x,
      height: position2.y - position.y,
    });
  }, [position2, position]);
  const onDrag = (props: any) => {
    if (props.clientX > 0 && props.clientY > 0) console.log(props);

    if (props.clientX > 0 && props.clientY > 0)
      setPosition({
        x: props.clientX,
        y: props.clientY - 70,
      });
    // setPosition();
  };

  const onDrag2 = (props: any) => {
    console.log(props.clientX, props.clientY);

    if (props.clientX > 0 && props.clientY > 0)
      setPosition2({
        x: props.clientX,
        y: props.clientY - 70,
      });
    // setPosition();
  };

  const onDragSizeStart = () => {
    setDisabled(true);
  };

  const onDragSizeEnd = () => {
    setDisabled(false);
  };

  const onElemDrag = (props: any) => {
    // if (props.clientX > 0 && props.clientY > 0)
    if (disabled) return;
    setMousePosition({
      x: props.screenX - props.clientX,
      y: props.screenY - props.clientY,
    });
    if (props.clientX > 0 && props.clientY > 0)
      setPosition({
        x: props.clientX - elemParams.width / 2,
        y: props.clientY - 70 - elemParams.height / 2,
      });
    if (props.clientX > 0 && props.clientY > 0)
      setPosition2({
        x: props.clientX + elemParams.width - elemParams.width / 2,
        y: props.clientY - 70 + elemParams.height - elemParams.height / 2,
      });
  };
  return {
    functions: {
      setPosition,
      onDrag,
      onDrag2,
      onElemDrag,
      onDragSizeStart,
      onDragSizeEnd,
    },
    states: { position, position2, elemParams },
  };
};

export default CreateImageQuizContainer;
