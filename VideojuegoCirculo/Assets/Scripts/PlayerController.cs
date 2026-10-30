using UnityEngine;

public class PlayerController : MonoBehaviour
{
    void Update()
    {
        int speed = 5;
        // Horizontal = A/D + Flechas Izquierda/Derecha
        float horizontal = Input.GetAxis("Horizontal");
        // Vertical = W/S + Flechas Arriba/Abajo
        float vertical = Input.GetAxis("Vertical");
        Vector3 movement = new Vector3(horizontal, vertical, 0f);
        // Normalizamos para que no corra más rápido en diagonal
        if (movement.magnitude > 1)
            movement.Normalize();
        transform.Translate(movement * speed * Time.deltaTime, Space.World);
    }
}
