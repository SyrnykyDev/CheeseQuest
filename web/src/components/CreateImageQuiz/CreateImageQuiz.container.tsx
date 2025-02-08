"use client";
import { useState } from "react";

const CreateImageQuizContainer = () => {
  const [position, setPosition] = useState({ x: 100, y: 0 });
  const [position2, setPosition2] = useState({ x: 250, y: 150 });

  const onDrag = (props: any) => {
    console.log(props);
    let img = new Image();
    img.src =
      "data:image/gif;base64,R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=";
    props.dataTransfer.setDragImage(img, 0, 0);

    if (props.clientX > 0 && props.clientY > 0)
      setPosition({
        x: props.clientX,
        y: props.clientY - 70,
      });
    // setPosition();
  };

  const onDrag2 = (props: any) => {
    console.log(props);

    let img = new Image();
    img.src =
      "data:image/gif;base64,R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=";
    props.dataTransfer.setDragImage(img, 0, 0);
    if (props.clientX > 0 && props.clientY > 0)
      setPosition2({
        x: props.clientX,
        y: props.clientY - 70,
      });
    // setPosition();
  };
  return {
    functions: { setPosition, onDrag, onDrag2 },
    states: { position, position2 },
  };
};

export default CreateImageQuizContainer;
