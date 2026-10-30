
using UnityEngine;

public class EnemyFollowController : MonoBehaviour
{
    public float speed = 3f;
    public Transform player;

    private Camera mainCam;

    void Start()
    {
        mainCam = Camera.main;
    }

    void Update()
    {
        if (player == null) return;

        // Dirección hacia el jugador
        Vector3 direction = player.position - transform.position;
        direction.z = 0f;

        // Girar hacia el jugador
        if (direction.sqrMagnitude > 0.001f)
        {
            float angle = Mathf.Atan2(direction.y, direction.x) * Mathf.Rad2Deg;
            transform.rotation = Quaternion.Euler(0f, 0f, angle - 90f);
        }

        // Moverse hacia el jugador
        transform.position = Vector3.MoveTowards(
            transform.position,
            player.position,
            speed * Time.deltaTime
        );

        // Mantenerse dentro de la cámara
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