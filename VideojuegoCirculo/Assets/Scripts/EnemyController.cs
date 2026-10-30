
using UnityEngine;

public class EnemyController : MonoBehaviour
{
    private Camera mainCam;

    public float speed = 3f;
    public float directionTime = 2f;

    private Vector3 direction;
    private float timer;

    void Start()
    {
        mainCam = Camera.main;
        PickNewDirection();
    }

    void PickNewDirection()
    {
        direction = new Vector3(
            Random.Range(-1f, 1f),
            Random.Range(-1f, 1f),
            0f
        ).normalized;

        timer = directionTime;
    }
    void RotateTowardsDirection()
{
    float angle = Mathf.Atan2(direction.y, direction.x) * Mathf.Rad2Deg;
    transform.rotation = Quaternion.Euler(0f, 0f, angle - 90f);
}

    void KeepInsideCamera()
    {
        Vector3 viewportPos = mainCam.WorldToViewportPoint(transform.position);

        bool hitX = false;
        bool hitY = false;

        if (viewportPos.x <= 0.05f || viewportPos.x >= 0.95f)
        {
            direction.x = -direction.x;
            hitX = true;
        }

        if (viewportPos.y <= 0.05f || viewportPos.y >= 0.95f)
        {
            direction.y = -direction.y;
            hitY = true;
        }

        viewportPos.x = Mathf.Clamp(viewportPos.x, 0.05f, 0.95f);
        viewportPos.y = Mathf.Clamp(viewportPos.y, 0.05f, 0.95f);

        transform.position = mainCam.ViewportToWorldPoint(viewportPos);

        if (hitX || hitY)
        {
            direction.Normalize();
        }
    }

    void Update()
    {
        transform.Translate(direction * speed * Time.deltaTime, Space.World);

        timer -= Time.deltaTime;

        if (timer <= 0)
        {
            PickNewDirection();
        }

        KeepInsideCamera();
        RotateTowardsDirection();
    }
}