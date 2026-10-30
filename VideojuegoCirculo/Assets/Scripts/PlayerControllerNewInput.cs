
using UnityEngine;
using UnityEngine.InputSystem;

public class PlayerControllerNewInput : MonoBehaviour
{
    public float speed = 5f;
    private Vector2 movement;
    private Camera mainCam;

    void Start()
    {
        mainCam = Camera.main;
    }

    void OnMove(InputValue value)
    {
        movement = value.Get<Vector2>();
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        Debug.Log("¡Muerte!");
    }
    void Update()
    {
        Vector3 direction = new Vector3(movement.x, movement.y, 0f);

        transform.Translate(direction * speed * Time.deltaTime, Space.World);

        KeepInsideCamera();
    }

    void KeepInsideCamera()
    {
        Vector3 viewportPos = mainCam.WorldToViewportPoint(transform.position);

        viewportPos.x = Mathf.Clamp(viewportPos.x, 0.05f, 0.95f);
        viewportPos.y = Mathf.Clamp(viewportPos.y, 0.05f, 0.95f);

        transform.position = mainCam.ViewportToWorldPoint(viewportPos);
    }
}